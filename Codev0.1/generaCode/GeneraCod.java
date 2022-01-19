package generaCode;
import ast.*;
import java.util.ArrayList;
import errors.GestionErroresExp;
import java.io.FileWriter;   
import java.io.IOException; 


public class GeneraCod {
    
    public static S buscaMain(S tree){
        SList list = (SList) tree;
        ArrayList<S> block = list.get();
        S main = null;
        for(S s: block){
            if(s.id() == TipoS.FUNCTION){
                if(((SFunction) s).iden().iden().equals("main")){
                    if(main != null)    
                        GestionErroresExp.warningSem(s.getUL(),"Definiendo mains repetidos! Solo se usará el último main");
                    main = s;
                }
                    
            }
        }
        return main;
    }
    public static void generar(S tree){
        System.out.println("### FASE DE GENERAR CODIGO ###");
        System.out.println("Calculando funcion delta");
        CalculaDelta.calcular(tree, 0);
        ArrayList<String> funciones = new ArrayList<String>();
        SFunction men = (SFunction) buscaMain(tree);
        if(men == null || ((TFuncion) men.tipo).getRet().id() != TipoT.VOID){
            System.out.println("***Error: Tu código no tiene funcion main, o este no es de tipo void!!");
            java.lang.System.exit(-1);
        }
        Code.codeI(tree, funciones); // para buscar las funciones
        //String mein = ConstantesWasm.surroundMain("main", men.maxMemory,  Code.codeI(men.getBlock(), null) );
        //funciones.add(mein);
        String programa = ConstantesWasm.surroundMainProgram(funciones);
        
        try {
            FileWriter myWriter = new FileWriter("generaCode/main.wat");
            myWriter.write(programa);
            myWriter.close();
            System.out.println("Codigo wat salvado en generaCode/main.wat");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

class ConstantesWasm{
    public static String surroundMainProgram(ArrayList<String> functions){
        String funciones = "";
        for(String s:functions)
            funciones += s;
        return "(module\n(type $_sig_i32i32i32 (func (param i32 i32 i32) ))\n(type $_sig_i32ri32 (func (param i32) (result i32)))\n(type $_sig_i32 (func (param i32)))\n(type $_sig_ri32 (func (result i32)))\n(type $_sig_void (func ))\n(import \"runtime\" \"exceptionHandler\" (func $exception (type $_sig_i32)))\n(import \"runtime\" \"print\" (func $print (type $_sig_i32)))\n(import \"runtime\" \"read\" (func $read (type $_sig_ri32)))\n(memory 2000)\n(global $SP (mut i32) (i32.const 0)) ;; start of stack\n(global $MP (mut i32) (i32.const 0)) ;; mark pointer\n(global $NP (mut i32) (i32.const 131071996)) ;; heap 2000*64*1024-4\n(start $main)\n(func $reserveStack (param $size i32)\n   (result i32)\n   get_global $MP\n   get_global $SP\n   set_global $MP\n   get_global $SP\n   get_local $size\n   i32.add\n   set_global $SP\n   get_global $SP\n   get_global $NP\n   i32.gt_u\n   if\n   i32.const 3\n   call $exception\n   end\n)\n(func $freeStack (type $_sig_void)\n   get_global $MP\n   i32.load\n   i32.load offset=4\n   set_global $SP\n   get_global $MP\n   i32.load\n   set_global $MP \n)\n"
                + funciones + "(func $copyn (type $_sig_i32i32i32) ;; copy $n i32 slots from $src to $dest\n   (param $src i32)\n   (param $dest i32)\n   (param $n i32)\n   block\n     loop\n       get_local $n\n       i32.eqz\n       br_if 1\n       get_local $n\n       i32.const 1\n       i32.sub\n       set_local $n\n       get_local $dest\n       get_local $src\n       i32.load\n       i32.store\n       get_local $dest\n       i32.const 4\n       i32.add\n       set_local $dest\n       get_local $src\n       i32.const 4\n       i32.add\n       set_local $src\n       br 0\n     end\n   end\n)" + ")";
    }
    public static String surroundMain(String name, int maxSize, String instructions){
        return "(func $" + name + " (type $_sig_void)\n    (local $temp i32)\n (local $temp2 i32)\n (local $temp3 i32)\n  (local $localsStart i32)\n   i32.const " + (maxSize + 8)
                + "\n call $reserveStack  ;; returns old MP (dynamic link)\n    set_local $temp\n    get_global $MP\n    get_local $temp\n    i32.store\n    get_global $MP\n    get_global $SP\n    i32.store offset=4\n    get_global $MP\n    i32.const 8\n    i32.add\n    set_local $localsStart\n\n"
                +  instructions + "\n call $freeStack)\n"; // las funciones void tambien devuelven algo, para que la limpieza de pila no tenga casos raros
    }
    public static String surroundFunction(String name, int maxSize, String instructions){
        return "(func $" + name + " (type $_sig_ri32) (result i32)\n    (local $temp i32)\n (local $temp2 i32)\n (local $temp3 i32)\n  (local $localsStart i32)\n   i32.const " + (maxSize + 8)
                + "\n call $reserveStack  ;; returns old MP (dynamic link)\n    set_local $temp\n    get_global $MP\n    get_local $temp\n    i32.store\n    get_global $MP\n    get_global $SP\n    i32.store offset=4\n    get_global $MP\n    i32.const 8\n    i32.add\n    set_local $localsStart\n\n"
                +  instructions + "\n i32.const -1 \n call $freeStack)\n"; // las funciones void tambien devuelven algo, para que la limpieza de pila no tenga casos raros
    }
    public static String surroundMethod(String name, int maxSize, String instructions){ //para los metodos de clases
        return "(func $" + name + " (type $_sig_i32ri32)\n (param $this i32) (result i32)\n    (local $temp i32)\n (local $temp2 i32)\n (local $temp3 i32)\n (local $localsStart i32)\n   i32.const " + (maxSize + 8)
                + "\n call $reserveStack  ;; returns old MP (dynamic link)\n    set_local $temp\n    get_global $MP\n    get_local $temp\n    i32.store\n    get_global $MP\n    get_global $SP\n    i32.store offset=4\n    get_global $MP\n    i32.const 8\n    i32.add\n    set_local $localsStart\n\n"
                +  instructions + "\n i32.const -1 \n call $freeStack)\n"; // las funciones void tambien devuelven algo, para que la limpieza de pila no tenga casos raros
    }
}
