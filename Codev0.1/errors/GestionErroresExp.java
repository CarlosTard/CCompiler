package errors;

import alex.UnidadLexica;

public class GestionErroresExp {
   public String errorLexico(int fila, int col, String lexema) {
      return "*ERROR fila:"+fila+ " col: " + col + ": Caracter inesperado: "+lexema; 
   }  
   public String errorSintactico(UnidadLexica unidadLexica) {
      return "**ERROR archivo: " + unidadLexica.fileName() + " fila: "+unidadLexica.fila()+ " col: "+unidadLexica.col()+": Elemento inesperado "+unidadLexica.lexema();
   }
   public static void errorSem(UnidadLexica unidadLexica, String errr) {
       if(unidadLexica == null)
        System.out.println("Error sin unidad lexica: " + errr);
       else System.out.println("***ERROR archivo: " + unidadLexica.fileName() + " fila: "+unidadLexica.fila()+ " col: "+unidadLexica.col()+": Error semántico: "+errr);
   }
   public static void warningSem(UnidadLexica unidadLexica, String error) {
       if(unidadLexica == null)
        System.out.println("Warning sin unidad lexica: " + error);
       else System.out.println("***Warning archivo: " + unidadLexica.fileName() + " fila: "+unidadLexica.fila()+ " col: "+unidadLexica.col()+": Warning semántico: "+error);
   }
}
