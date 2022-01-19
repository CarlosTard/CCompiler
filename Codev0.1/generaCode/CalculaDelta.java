package generaCode;
import ast.S;
import ast.SList;
import ast.SIf;
import ast.SLoop;
import ast.SSwitch;
import ast.TipoS;
import ast.SClase;
import ast.STipoIden;
import ast.SCase;
import ast.SFunction;
import java.util.ArrayList;
import errors.GestionErroresExp;

class MaxMemory{
    int c = 0;
    int mx = 0;
    
    public MaxMemory(int c, int mx){
        this.c = c;
        this.mx = mx;
    }
}

public class CalculaDelta{ // calculamos a la vez el delta y el max memory
    public static int calcular(S instrs, int actualDelta){
        return calcular(instrs,actualDelta, new MaxMemory(0,0));
    }
    public static int calcular(S instrs, int actualDelta, MaxMemory mm){
//LIST,STIPOIDEN,ENUMELEMENT,ERROR, DECL, IF, RETURN, CONTINUE, BREAK, PRINT, PRUNT, WHILE, FOR, CLASS, FUNCTION, E, USING, ENUM, SWITCH, CASE, ASIG
        
        if(instrs.id() == TipoS.LIST){ // bloque de instrucciones
            SList list = (SList) instrs;
            ArrayList<S> block = list.get();
            int nextDelta = actualDelta;
            MaxMemory m1 = new MaxMemory(0,0);
            for(S s: block){
                nextDelta = calcular(s, nextDelta, m1);
            }
            if(m1.mx + mm.c > mm.mx)
                mm.mx = m1.mx + mm.c;
            return actualDelta;
            
        }else if(instrs.id() == TipoS.FUNCTION){
            SFunction fun = (SFunction) instrs;
            int nextDelta = 0;
            MaxMemory mam = new MaxMemory(0,0);
            for(S s: fun.getArgs()){
                nextDelta = calcular(s,nextDelta, mam);
            }
            nextDelta = calcular(fun.getBlock(),nextDelta,mam);
            fun.maxMemory = mam.mx;
            
        }else if(instrs.id() == TipoS.DECL){
            instrs.delta = actualDelta;
            mm.c += instrs.tipo.size();
            mm.mx += instrs.tipo.size();
            return actualDelta + instrs.tipo.size();
        }else if(instrs.id() == TipoS.CLASS){
            SClase clas = (SClase) instrs;
            //clas.delta = actualDelta;
            ArrayList<S> atribs =  clas.getAttrList();
            ArrayList<S> funcs = clas.getFunctionList();
            int i = 0;
            for(S s: atribs){
                i = calcular(s,i, mm);
            }
            if(funcs != null){
                for(S s: funcs){
                    calcular(s,i, mm);
                }
            }   
            return actualDelta;
        }else if(instrs.id() == TipoS.STIPOIDEN){
            instrs.delta = actualDelta;
            mm.c += instrs.tipo.size();
            mm.mx += instrs.tipo.size();
            return actualDelta + instrs.tipo.size();
        }else if(instrs.id() == TipoS.IF){
            S then = ((SIf) instrs).getThen();
            S elsee = ((SIf) instrs).getElse();
            int nextDelta = actualDelta;
            
            if(then != null){
                nextDelta = calcular(then,actualDelta, mm);
            }
            if(elsee != null){
                nextDelta = calcular(elsee, actualDelta, mm);
            }
            return actualDelta;
            
        }
        else if(instrs.id() == TipoS.WHILE){
            S insts = ((SLoop) instrs).getInstructs();
            if (insts != null){
                calcular(insts, actualDelta, mm);
            }
            return actualDelta;
        }
        else if(instrs.id() == TipoS.FOR){
            S insts = ((SLoop) instrs).getInstructs();
            int nextDelta = actualDelta;
            if(((SLoop) instrs).getDec() != null)
                nextDelta = calcular(((SLoop) instrs).getDec(), actualDelta, mm);
            if (insts != null){
                calcular(insts, nextDelta, mm);
            }
            return actualDelta;
            
        }
        else if(instrs.id() == TipoS.SWITCH){
            ArrayList<S> cases = ((SSwitch) instrs).getCases();
            S def = ((SSwitch) instrs).getDef();
            for(S s: cases){
                calcular(s,actualDelta, mm);
            }
            
            if(def != null){
                calcular(def, actualDelta, mm);
            }
            return actualDelta;
            
        }
        else if(instrs.id() == TipoS.CASE){
            calcular(((SCase) instrs).getBlock(), actualDelta, mm);
            return actualDelta;
        }
        return actualDelta;
    }
}
