package ast;
import java.util.ArrayList;
import alex.UnidadLexica;
import asem.PilaTablas;

public abstract class E  {
    protected UnidadLexica ulex;
    public T tipo = null;
    public S link = null;
    public void setUL(UnidadLexica ulex) {
        this.ulex = ulex;   
    }
    public UnidadLexica getUL(){
        return this.ulex;
    }
    public int fila() {return ulex.fila();}
    public int col() {return ulex.col();}
    
    public abstract TipoE id();
    public abstract void vincula(PilaTablas pt);
    public abstract boolean chequea();
    public E opnd1() {throw new UnsupportedOperationException("opnd1");} 
    public E opnd2() {throw new UnsupportedOperationException("opnd2");}
    public ArrayList<E> opndList() {throw new UnsupportedOperationException("opndList");}
    public String num() {throw new UnsupportedOperationException("num");}
    public String bool() {throw new UnsupportedOperationException("bool");}

}
