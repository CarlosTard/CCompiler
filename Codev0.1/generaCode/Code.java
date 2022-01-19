package generaCode;
import ast.*;
import java.util.ArrayList;
import errors.GestionErroresExp;

public class Code{
    private static boolean inLoop = false;
    private static boolean inIf = false;
    private static int numBlocks = -1;
    private static int currBlock = -1;
    private static S condFor = null; // Para evitar bucles infinitos con continue guardo la condición de avance del for
    public static String codeI(S instrs, ArrayList<String> funciones){
        String sol;
       
        if(instrs.id() == TipoS.LIST){ // bloque de instrucciones
            SList list = (SList) instrs;
            ArrayList<S> block = list.get();
            sol = "";
            for(S s: block){
                if(condFor != null && s.id() == TipoS.CONTINUE)
                    sol = sol + codeI(condFor,funciones);
                sol = sol + codeI(s, funciones);
            }
            return sol;
            
        }
        else if(instrs.id() == TipoS.FUNCTION){
            SFunction fun = (SFunction) instrs;
            sol = "";
            String name = fun.funcName;
            int maxSize = fun.maxMemory * 4;
            S instr = fun.getBlock();
            if(((TFuncion)fun.tipo).getRet().size() > 1){
                GestionErroresExp.errorSem(fun.getUL(),"Las funciones no pueden devolver más de 32 bits");
                java.lang.System.exit(-1);
            }
            if(((SFunction)fun).iden().iden().equals("main"))
                funciones.add(ConstantesWasm.surroundMain(name, maxSize, codeI(instr, funciones)));
            else
                funciones.add(ConstantesWasm.surroundFunction(name, maxSize, codeI(instr, funciones)));
        }
        else if(instrs.id() == TipoS.DECL){
            SDecl dec = (SDecl) instrs;
            String d = codeD(dec.iden(), funciones);
            if(dec.ladoDer() != null){
                String e = codeE(dec.ladoDer(), funciones);
                return d + "" + e + "i32.store     ;;Declaracion\n";
            }
            return "    ;;Declaracion sin lado derecho\n";
        }
        else if(instrs.id() == TipoS.ASIG){
            SAsig dec = (SAsig) instrs;
            String d = codeD(dec.ladoIz(), funciones);
            String e = codeE(dec.ladoDer(), funciones);
            return d + "" + e + "i32.store     ;;Asignacion\n";
        }
        else if(instrs.id() == TipoS.CLASS){
            SClase clas = (SClase) instrs;
            if(clas.getFunctionList() == null)
                return "";
            for(S s: clas.getFunctionList()){ // no podemos llamar a codeI recursivamente, porque las funciones de clases se tratan distinto
                SFunction fun = (SFunction) s;
                sol = "";
                String name = fun.funcName;
                int maxSize = fun.maxMemory * 4;
                S instr = fun.getBlock();
                if(((TFuncion)fun.tipo).getRet().size() > 1){
                    GestionErroresExp.errorSem(fun.getUL(),"Las funciones no pueden devolver más de 32 bits");
                    java.lang.System.exit(-1);
                }
                funciones.add(ConstantesWasm.surroundMethod(name, maxSize, codeI(instr, funciones)));
            }
        }
        else if(instrs.id() == TipoS.IF){
            E cond = ((SIf) instrs).getCond();
            S then = ((SIf) instrs).getThen();
            S elsee = ((SIf) instrs).getElse();
            sol = codeE(cond, funciones) + "if    ;;If\n";
            if(then != null)
                sol = sol + codeI(then, funciones) + ";;bloque then\n";
            if(elsee != null)
                sol = sol + "else    ;;bloque else\n" + codeI(elsee, funciones);
           
            sol = sol  + "end   ;;finIf\n";
            return sol;
        }
        else if(instrs.id() == TipoS.WHILE){
            S insts = ((SLoop) instrs).getInstructs();
            E cond = ((SLoop) instrs).getCond();
            inLoop = true;
            sol = "block $fin \n" + "loop $init ;;While\n" + codeE(cond, funciones) + "i32.eqz\n" + "br_if 1\n" + codeI(insts, funciones) + "br 0\n" + "end ;;finWhile\n" + "end\n";
            inLoop = false;
            return sol;
        }
        else if(instrs.id() == TipoS.FOR){
            S insts = ((SLoop) instrs).getInstructs();
            S dec = ((SLoop) instrs).getDec();
            E cond = ((SLoop) instrs).getCond();
            S inc = ((SLoop) instrs).getInc();
            SList instList = new SList();
            instList.add(insts);
            instList.add(inc);
            
            inLoop = true;
            condFor = inc;
            sol = codeI(dec, funciones) + "block $fin\n" + "loop  $init ;;For\n" + codeE(cond, funciones) + "i32.eqz\n" + "br_if 1\n" + codeI(instList, funciones) + "br 0\n" + "end ;;finFor\n" + "end\n";
            condFor = null;
            inLoop = false;
            return sol;
        }
        else if(instrs.id() == TipoS.SWITCH){
            ArrayList<S> cases = ((SSwitch) instrs).getCases();
            S def = ((SSwitch) instrs).getDef();
            int n = cases.size();

            String res = "";
            for(int i= 0;i < n+2;++i){
                res += "block\n";
            }
            res += codeE(((SSwitch) instrs).getVar(), funciones); // evaluamos la condicion
            res += "set_local $temp2 ;;Condicion switch \n";
            int  i= 0;
            for(S s: cases){    //condiciones de los cases
                res += codeE(((SCase) s).getVar(), funciones);
                res += "get_local $temp2 ;; Condicion " + i + " Switch \n"; 
                res += "i32.eq  ;;\n";
                res += "br_if " + i + "\n";
                ++i;
            }
            res += "br " + (n) + "\n"; // el salto al default
            i = 0;
            for(S s: cases){    //bloques de los cases
                res += "end;;empieza el case \n";
                res += codeI(((SCase) s).getBlock(), funciones);
                res += "br " + (n - i) + "\n";
                ++i;
            }
            res += "end;;default\n";
    
            if(def != null){
                res += codeI(((SSwitch) instrs).getDef(), funciones);
            }

            res = res + "end ;;final switch\n";
            return res;
            
        }
        else if(instrs.id() == TipoS.RETURN){
            E ret = ((SReturn) instrs).getE();
            sol = "return  ;;return\n";
            if(ret == null)
                return sol;
            return codeE(ret,funciones) + sol;
        }
        else if(instrs.id() == TipoS.CONTINUE){
            return "br $init    ;;continue\n";
        }
        else if(instrs.id() == TipoS.BREAK){
            return "br $fin    ;;break\n";

        }
        else if(instrs.id() == TipoS.PRINT){
            SPrint s = (SPrint) instrs;
            sol = "";
            if(s.getE() != null){
                String e = codeE(s.getE(), funciones);
                sol = e;
            }
            return sol + "call $print     ;;print\n";
        }
        else if(instrs.id() == TipoS.PRUNT){
            SPrunt instr = (SPrunt) instrs;
            EIden e = instr.getIden();
            return codeD(e, funciones) + "call $read\n" + "      ;;prunt\n i32.store\n";
        }
        else if(instrs.id() == TipoS.E){ // ++ y --
            E e = ((SE) instrs).getE();
            return codeE(e, funciones) + "set_local $temp3\n";
        }
        return "";
    }
    public static String codeD(E desig, ArrayList<String> funciones){
    
        switch(desig.id()){
        case IDEN: // identificador
            if(desig.link.getEstoyDentroDeClase()){ // es atributo del objeto donde estamos actualmente, luego hay que usar el param $this en vez de localsStart
                return "i32.const " + desig.link.delta*4 + "\nget_local $this \n" + "i32.add\n";
            }
            return "i32.const " + desig.link.delta*4 + "\nget_local $localsStart \n" + "i32.add\n";
        case DEREF: // operador *
            return codeD(((EUn) desig).opnd1(), funciones) + "i32.load \n";
        case INSTANCE: // operador .
            EBin punto = (EBin) desig;
            return codeD(punto.opnd1(), funciones) + "i32.const " + desig.link.delta*4 + "\n i32.add\n";
        case SUBINDICE: // acceso a arrays
            EBin exp = (EBin) desig;
            String inDir = codeD(exp.opnd1(), funciones);
            int tipoSize = exp.tipo.size();
            String offset = codeE(exp.opnd2(), funciones);
            return inDir + "i32.const " + 4*tipoSize + "\n" + offset + "i32.mul\n i32.add\n";
        }
        return "";
    }
    public static String codeE(E expr, ArrayList<String> funciones){
        String op1;
        String op2;
    
        switch(expr.id()){
        case SUMA:
            op1 = codeE(((EBin )expr).opnd1(), funciones);
            op2 = codeE(((EBin )expr).opnd2(), funciones);
            return op1 + op2 + "i32.add \n";
        case RESTA:
            op1 = codeE(((EBin )expr).opnd1(), funciones);
            op2 = codeE(((EBin )expr).opnd2(), funciones);
            return op1 + op2 + "i32.sub \n";
        case NEW:
            break;
        case MUL:
            op1 = codeE(((EBin )expr).opnd1(), funciones);
            op2 = codeE(((EBin )expr).opnd2(), funciones);
            return op1 + op2 + "i32.mul \n";
        case DIV:
            op1 = codeE(((EBin )expr).opnd1(), funciones);
            op2 = codeE(((EBin )expr).opnd2(), funciones);
            return op1 + op2 + "i32.div_s \n";
        case POW:
            break;
        case MOD:
            break;
        case OPUESTO:
            op1 = codeE(((EUn )expr).opnd1(), funciones);
            return "i32.const 0\n" + op1 + "i32.sub \n";
        case AND:
            op1 = codeE(((EBin )expr).opnd1(), funciones);
            op2 = codeE(((EBin )expr).opnd2(), funciones);
            return op1 + op2 + "i32.and \n";
        case OR:
            op1 = codeE(((EBin )expr).opnd1(), funciones);
            op2 = codeE(((EBin )expr).opnd2(), funciones);
            return op1 + op2 + "i32.or \n";
        case NOT:
            op1 = codeE(((EUn )expr).opnd1(), funciones);
            return op1 + "i32.eqz \n";
        case IGUALLOG:
            op1 = codeE(((EBin )expr).opnd1(), funciones);
            op2 = codeE(((EBin )expr).opnd2(), funciones);
            return op1 + op2 + "i32.eq \n";
        case NOTIGUAL:
            op1 = codeE(((EBin )expr).opnd1(), funciones);
            op2 = codeE(((EBin )expr).opnd2(), funciones);
            return op1 + op2 + "i32.ne \n";
        case MENOR:
            op1 = codeE(((EBin )expr).opnd1(), funciones);
            op2 = codeE(((EBin )expr).opnd2(), funciones);
            return op1 + op2 + "i32.lt_s \n";
        case MENORIGUAL:
            op1 = codeE(((EBin )expr).opnd1(), funciones);
            op2 = codeE(((EBin )expr).opnd2(), funciones);
            return op1 + op2 + "i32.le_s \n";
        case MAYOR:
            op1 = codeE(((EBin )expr).opnd1(), funciones);
            op2 = codeE(((EBin )expr).opnd2(), funciones);
            return op1 + op2 + "i32.gt_s \n";
        case MAYORIGUAL:
            op1 = codeE(((EBin )expr).opnd1(), funciones);
            op2 = codeE(((EBin )expr).opnd2(), funciones);
            return op1 + op2 + "i32.ge_s \n";
        case NUM:
            return "i32.const " + ((ENum) expr).num() + "\n";
        case BOOL:
            if(((EBool) expr).bool().equals("true"))
                return "i32.const 1\n  ;;true \n";
            else return "i32.const 0\n  ;;false \n";
        case IDEN:
            return codeD(expr, funciones) + "i32.load \n"; // esto es para un entero
        case MASMAS:
            E ope1 = ((EUn) expr).opnd1();
            E ope2 = new ENum("1",false);
            return codeI(new SAsig(ope1,new EBin(ope1,ope2,TipoE.SUMA)), funciones) + codeE(ope1, funciones);
        case MENOSMENOS:
            E oper1 = ((EUn) expr).opnd1();
            E oper2 = new ENum("1",false);
            return codeI(new SAsig(oper1,new EBin(oper1,oper2,TipoE.RESTA)), funciones) + codeE(oper1, funciones);
        case DIRMEM: //operador &
            return codeD(((EUn) expr).opnd1(), funciones);
        case DEREF: // operador *
            return codeD(expr, funciones) + "i32.load \n";
        case INSTANCE: //operador .
            return codeD(expr, funciones) + "i32.load \n";
        case SUBINDICE: //acceso a arrays
            return codeD(expr, funciones) + "i32.load \n";
        case FUNCTION: // llamada a funcion
            EBin llamada = (EBin) expr;
            EList listaArgs = (EList) llamada.opnd2();
            boolean estamosLlamandoAObjeto = false;
            String objeto = "";
            String functionName = "";
            if(llamada.opnd1().getClass() == EBin.class){
                estamosLlamandoAObjeto = true;
                objeto = codeD(llamada.opnd1(),funciones);
                functionName = ((SFunction)((EIden) ((EBin) llamada.opnd1()).opnd2()).link).funcName;
            }else if(llamada.opnd1().link.getEstoyDentroDeClase()){ // estamos haciendo una llamada a una función dentro del propio objeto
                estamosLlamandoAObjeto = true;
                objeto = codeD(llamada.opnd1(),funciones);
                functionName = ((SFunction) ((EIden) llamada.opnd1()).link).funcName;
            }else
                functionName = ((EIden) llamada.opnd1()).iden();
            ArrayList<E> lista = listaArgs.get();
            String args = "";
            
            for(int i = lista.size() -1; i >= 0;--i){ // antes de nada, calculamos todos los argumentos que sean expresion.
                                            // se hace al principio para permitir funcion1(funcion2());
                E e = lista.get(i);
               if(e.tipo.id() == TipoT.INT || e.tipo.id() == TipoT.PUNTERO || e.tipo.id() == TipoT.BOOL ){                    
                    args += codeE(e,funciones);
                }
            }
            args += "get_global $SP\n i32.const 8\n i32.add\n set_local $temp\n"; // temp es la posicion del siguiente argumento
                     
            for(E e: lista){ // 2 tipos: expresiones(hay que evaluar y luego copiar de la pila) vs argumentos que hay que copiar de memoria(arrays, objetos, etc)
                int tamArg = e.tipo.size();
                if(e.tipo.id() == TipoT.INT || e.tipo.id() == TipoT.PUNTERO || e.tipo.id() == TipoT.BOOL ){                    // son los unicos que se pueden pasar desde la pila??
                    args = args + "set_local $temp3\n get_local $temp\n get_local $temp3\n i32.store\n ";
                }else if(e.tipo.id() == TipoT.ARRAY || e.tipo.id() == TipoT.CLASE || e.tipo.id() == TipoT.IDEN){
                    //copiar desde memoria
                    args += codeD(e, funciones) + "\n get_local $temp \n" + "i32.const " + tamArg + "\n call $copyn\n"; 
                }else{
                    GestionErroresExp.errorSem(llamada.getUL(),"Llamando a funcion:" + functionName + " con argumento de tipo no admitido: " + e.tipo.id());
                }
                args += "get_local $temp\n i32.const " + tamArg*4 + "\n i32.add\n set_local $temp\n"; // movemos temp para el siguiente argumento
            }
            if(!estamosLlamandoAObjeto)
                return args + "call $" + functionName  + ";; Llamada a funcion \n ";
            else // si llamamos a metodo de objeto, hay que pasarle la direccion del objeto
                return args + objeto + "call $" + functionName  + ";; Llamada a funcion de objeto \n ";

        case LIST:
            break;
        }
        return "";
    }
}
