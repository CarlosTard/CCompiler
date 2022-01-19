package alex;

import java_cup.runtime.Symbol;

public class UnidadLexica extends Symbol {
   private int fila;
   private int col;
   private String nombreClase;
   private String lexema;
   private String fileName; // para guardar de qué archivo es cada import
   public UnidadLexica(int fila,int col, int clase, String lexema, String nombreClase, String fileName) {
     //super(clase,new TokenValue(lexema, fila,col,nombreClase,fileName));
     super(clase,lexema);
     this.value = this;
	 this.fila = fila;
     this.col = col;
     this.nombreClase = nombreClase;
     this.fileName = fileName;
     this.lexema = lexema;
   }
   public int clase () {return sym;}
   public String lexema() {return lexema;}
   public int fila() {return fila;}
   public int col() {return col;}
   public String fileName(){ return this.fileName;};
   public String toString() {
       return "[archivo:" + fileName + " clase:"+this.nombreClase+",fila:"+fila()+",col:"+col()+",lexema:"+lexema()+"]";  
  }
}
