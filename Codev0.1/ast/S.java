package ast;
import alex.UnidadLexica;
import java.util.HashMap;
import asem.PilaTablas;
import errors.GestionErroresExp;

public abstract class S { // clase de la que tienen que heredar las sentencias
    private TipoS id;
    public int delta; // la posicion relativa de una declaracion (solo para declaraciones de variables, clases
    public int maxMemory;
    private boolean estoyDentroDeClase = false; //solo lo usan las funciones y las declaraciones de las clases
    
    protected UnidadLexica ulex;
    public T tipo = null;

    public S(TipoS id){
        this.id = id;
    }
    
    public void setUL(UnidadLexica ulex) {
        this.ulex = ulex;   
    }
    public UnidadLexica getUL(){
        return this.ulex;
    }
    public int fila() {return ulex.fila();}
    public int col() {return ulex.col();}

    public TipoS id(){return id;}

    public abstract String toString(int h);
    public String toString(){return toString(0);};
    public abstract void vincula(PilaTablas pt);
    public abstract boolean chequea();
    protected String getTabs(int h){ 
        String tabs = "    ";
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < h; ++i)
            sb.append(tabs);
        return sb.toString();
    }
    public void setEstoyDentroDeClase(){
        this.estoyDentroDeClase = true;
    }
    public boolean getEstoyDentroDeClase(){
        return this.estoyDentroDeClase;
    }
    
   protected void printError(String error){
       GestionErroresExp.errorSem(this.ulex, error);
   }
}
