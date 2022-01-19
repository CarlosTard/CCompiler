package ast;
import alex.UnidadLexica;
import asem.PilaTablas;

public abstract class T { // clase de la que tienen que heredar las sentencias
    protected TipoT id;
    public S link = null;
    protected UnidadLexica ulex = null;
    public T(TipoT id){
        this.id = id;
    }

    public void setUL(UnidadLexica ulex) {
        this.ulex = ulex;   
    }
    public UnidadLexica getUL(){
        return this.ulex;
    }
    
    public T getSub(){
        throw new UnsupportedOperationException("Calling getSub on T");
    }
    public int fila() {return ulex.fila();}
    public int col() {return ulex.col();}
    public TipoT id(){return id;}
    public abstract String toString();
    
    //devolvemos T por si hay un identificador vinculado a un using. Vincula devuelve el tipo del using al que está vinculado el identificador de tipo
    public abstract T vinculaT(PilaTablas pt); 
    public abstract boolean chequea();
    public abstract boolean mismoTipo(T otro);
    public abstract int size();

}
