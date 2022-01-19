package ast;
import asem.PilaTablas;
import errors.GestionErroresExp;

public class TPuntero extends T {
    private T subtipo;
    public TPuntero(T sub){
        super(TipoT.PUNTERO);
        this.subtipo = sub;
    }
    public String toString(){
        return '(' + subtipo.toString() + ')' + '*';
    }
    public T getSub(){
        return subtipo;
    }
    
    public T vinculaT(PilaTablas pt){
         this.subtipo = subtipo.vinculaT(pt);
         return this;
    }
    
     public boolean chequea(){
         boolean ok = subtipo.chequea();
         if(subtipo.id() == TipoT.VOID){
             GestionErroresExp.errorSem(this.ulex,"Chequeo de tipos fallido. Puntero a VOID");
                return false;
         }
         return ok; 
    }
     public boolean mismoTipo(T otro){
        return otro.id() == TipoT.PUNTERO && subtipo.mismoTipo(otro.getSub());
    }
      @Override
    public int size(){
        return 1;
    }
}
