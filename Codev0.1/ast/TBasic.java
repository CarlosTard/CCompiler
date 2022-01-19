package ast;
import asem.PilaTablas;

public class TBasic extends T {
    public TBasic(TipoT id){
        super(id);
    }
    public String toString(){
        return this.id().name();
    }
    public T vinculaT(PilaTablas pt){
        return this;
    }
    public boolean chequea(){
         return true;
    }
    
    public boolean mismoTipo(T otro){
        if(otro == null)
            return false;
        return otro.id() == this.id() || otro.id() == TipoT.FLOAT && this.id() == TipoT.INT || otro.id() == TipoT.INT && this.id() == TipoT.FLOAT;
    }
    
    @Override
    public int size(){
        return 1;
    }
}
