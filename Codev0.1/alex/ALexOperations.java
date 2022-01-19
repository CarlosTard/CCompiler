package alex;

import java.util.HashMap;
import java.lang.reflect.Field;
import constructorast.ClaseLexica;

public class ALexOperations {
  private AnalizadorLexicoExp alex;
  private HashMap<Integer,String> claseLexicaNames;
  private String fileName;
  public ALexOperations(AnalizadorLexicoExp alex) {
    this.alex = alex;   
    this.claseLexicaNames = new HashMap<Integer,String>();
    try {
        for (Field field : ClaseLexica.class.getDeclaredFields()) {
            String name = field.getName();
            int value = field.getInt(null);
            this.claseLexicaNames.put(value,name);
            
        }
    }
    catch (IllegalAccessException e) {
        System.out.println(e);
    }
  }
  public void setFileName(String file){
      this.fileName = file;
  }
  public UnidadLexica ul(int clase, String lexema){
      return new UnidadLexica(alex.fila(),alex.col(), clase,lexema,claseLexicaNames.get(clase), this.fileName); 
  }
  
  /*
  public UnidadLexica unidadEnt() {
     return new UnidadLexica(alex.fila(),ClaseLexica.NUM,alex.lexema()); 
  } 
  public UnidadLexica unidadSuma() {
     return new UnidadLexica(alex.fila(),ClaseLexica.MAS,"+"); 
  } 
  public UnidadLexica unidadMul() {
     return new UnidadLexica(alex.fila(),ClaseLexica.POR,"*"); 
  } 
  public UnidadLexica unidadPAp() {
     return new UnidadLexica(alex.fila(),ClaseLexica.PAP,"("); 
  } 
  public UnidadLexica unidadPCierre() {
     return new UnidadLexica(alex.fila(),ClaseLexica.PCIERRE,")"); 
  } 
  public UnidadLexica unidadEof() {
     return new UnidadLexica(alex.fila(),ClaseLexica.EOF,"<EOF>"); 
  }*/
}
