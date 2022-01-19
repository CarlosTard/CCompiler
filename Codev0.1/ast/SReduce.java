package ast;
import java.util.ArrayList;
import alex.UnidadLexica;
import asem.PilaTablas;
import errors.GestionErroresExp;

public class SReduce { /*
    /**
     * Clase auxiliar, para solventar reduce/reduce del tipo iden[expresion][expresion]...
     * esta clase puede guardar un identificador, iden, o puede guardar una expresion(en cuyo caso, simboliza el offset de un array)
     * 
     * iden[expresion][expresion] puede ser un tipo array (en cuyo caso expresion debe ser constante) 
     * iden[expresion][expresion] puede ser el acceso a un array para asignaciones
    */
    private String iden;
    private SReduce type;
    private E offset;
    protected UnidadLexica ulex;
    
    public SReduce(String iden){
        this.iden = iden;
    }
    
    public SReduce(SReduce array, E of){
        this.iden = null;
        this.type = array;
        this.offset = of;
    }
    public void setUL(UnidadLexica ulex) {
        this.ulex = ulex;   
    }
    public int fila() {return ulex.fila();}
    public int col() {return ulex.col();}
    
    public T toT(){ //dos casos: es identificador, o es array de un subtipo type
        T ret;
        if(type == null){
            //EIden e = new EIden(iden);
            //e.setUL(this.ulex);
            ret = new TIden(iden);
        }else 
            ret = new TArray(type.toT(), offset);
        ret.setUL(this.ulex);
        return ret;
    }
    
    public E toE(){
        E ret;
        if(type == null)
            ret = new EIden(iden);
        else 
            ret = new EBin(type.toE() ,offset,TipoE.SUBINDICE);
        ret.setUL(this.ulex);
        return ret;
    }
    public String toString(int h){
        throw new UnsupportedOperationException("Calling toString on SReduce");
    }
    public void vincula(PilaTablas pt){
        throw new UnsupportedOperationException("Calling vincula on SReduce");
    }
    public boolean chequea(){
        throw new UnsupportedOperationException("Calling chequea on SReduce");
    }
}
