package ast;
import asem.PilaTablas;
import java.util.ArrayList;

public class TEnum extends T {
    private SEnum se;
    public TEnum(SEnum se){
        super(TipoT.ENUM);
        this.se = se;
    }
    public String toString(){
        return se.getIden().toString() ;
    }
    public SEnum se(){
        return se;
    }

    public T vinculaT(PilaTablas pt){
         throw new UnsupportedOperationException("Calling vinculaT on TEnum");
    }
     public boolean chequea(){
         return true;
    }
     public boolean mismoTipo(T otro){
        if(otro.id() != TipoT.ENUM )
            return false;
        TEnum otr = (TEnum) otro;
        return otr.se() == this.se;
    }
    
      @Override
    public int size(){
        return 1;
    }
}
