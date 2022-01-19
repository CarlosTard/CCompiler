package constructorast;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.FileNotFoundException;
import java.util.HashSet;

import alex.UnidadLexica;
import alex.AnalizadorLexicoExp;
import ast.S;
import ast.SList;
import asem.PilaTablas;
import generaCode.GeneraCod;

public class Main {
    private static HashSet<String> cache = new HashSet<String>(); // caché para los archivos ya cargados
    public static S load(String fileName) { //Carga un archivo, le pasa los analizadores lexicos y semantico, y devuelve el AST
        if(cache.contains(fileName)){
            System.out.println("* WARNING: multiple includes of file: " + fileName);
            return new SList();
        }
        cache.add(fileName);
        System.out.println("*--------- Log analizador Lexico: " + fileName + "---------*");
        Reader input;
        try{
            input = new InputStreamReader(new FileInputStream(fileName));
        }catch(FileNotFoundException e){
            System.out.println("***File not found: " + fileName + "***");
            return null; // el null para el analisis sintactico(de este modulo) y el analisis semantico del modulo padre
        }
        AnalizadorLexicoExp alex = new AnalizadorLexicoExp(input);
        alex.setFileName(fileName);
        UnidadLexica unidad = null;
        do {
            try{
                unidad = alex.next_token();  //Imprimimos información de las unidades Léxicas (las correctas)
            }catch(Exception e){
                e.printStackTrace();
            }
            System.out.println(unidad);
        }
        while (unidad.clase() != ClaseLexica.EOF);
        
        System.out.println();
        if(alex.hayError){
            System.out.println("Errores lexicos fichero: " + fileName + " Parando la compilacion");
            alex.printErrores(); //Imprimimos información de las unidades Léxicas (las incorrectas)
            return null; // el null para el analisis sintactico(de este modulo) y el analisis semantico del modulo padre
        }

        try{
            input = new InputStreamReader(new FileInputStream(fileName));
        }catch(Exception e){
            e.printStackTrace();
        }
        alex = new AnalizadorLexicoExp(input);
        alex.setFileName(fileName);
        ConstructorASTExp constructorast = new ConstructorASTExp(alex);
        
        S res = null;
        try{
            res = (S) constructorast.parse().value;
        }catch(Exception e){
            System.out.println("**Error sintactico: excepcion al construir el ast file: " + fileName);
        }
        if(constructorast.hayError){
            constructorast.printErrores(fileName);
            return null;
        }
        return res;
        
    }
    public static void main(String[] args)  {
        
         S tree = load(args[0]);
         if(tree == null) return; //Ha habido errores léxicos o sintácticos. Paramos la compilación.
         System.out.println(tree);
         System.out.println("### FASE DE VINCULACIÓN ###");
         PilaTablas pt = new PilaTablas();
         tree.vincula(pt);
         if(pt.hayError){
            System.out.println("Se han producido errores de vinculación. Parando compilación");
            return;
        }
        System.out.println("### FASE DE VINCULACIÓN FINALIZADA EXITOSAMENTE ###");
        System.out.println("### FASE DE CHEQUEO ###");
        if(!tree.chequea()){
            System.out.println("Se han producido errores en el chequeo de tipos. Parando compilación");
            return;
        }
        System.out.println("### FASE DE CHEQUEO FINALIZADO EXITOSAMENTE ###");
        GeneraCod gc = new GeneraCod();
        gc.generar(tree);
    }
}   
   
