package ast;
import asem.PilaTablas;
import errors.GestionErroresExp;

public class TArray extends T {
    private T subtipo;
    private E off = null;
    private int offs;
    public TArray(T sub){
        super(TipoT.ARRAY);
        this.subtipo = sub;
    }
    public TArray(T sub, E off){
        super(TipoT.ARRAY);
        this.subtipo = sub;
        this.off = off;
    }
    public String toString(){
        if(off == null) return '(' + subtipo.toString() + ')' + "[]";
        
        return '(' + subtipo.toString() + ')' + '[' + off +  ']';
    }
    public T getSub(){
        return subtipo;
    }
    public E getOff(){
        return off;
    }
    public int getOffs(){
        return offs;
    }
    public T vinculaT(PilaTablas pt){
         this.subtipo = subtipo.vinculaT(pt);
         return this;
    }
    public boolean chequea(){
         boolean ok = subtipo.chequea();
         if(off != null){
             if(!off.chequea())
                ok = false;
            else{
                if(off.tipo.id() != TipoT.INT || off.id() != TipoE.NUM){
                    GestionErroresExp.errorSem(this.ulex,"Chequeo de tipos fallido. Array con subíndice no entero o no constante");
                    ok = false;
                }else{
                    try { 
                        offs = Integer.parseInt(((ENum) off).num()); 
                    } catch(NullPointerException | NumberFormatException e) { 
                        GestionErroresExp.errorSem(this.ulex,"Chequeo de tipos fallido. Array con subíndice no entero constante");
                        ok = false;
                    }
                }
            }
         }
         if(subtipo.id() == TipoT.VOID){
             GestionErroresExp.errorSem(this.ulex,"Chequeo de tipos fallido. Puntero a VOID");
                return false;
         }
         return ok; 
    }
    public boolean mismoTipo(T otro){
        return otro.id() == TipoT.ARRAY && subtipo.mismoTipo(otro.getSub()) && ((TArray) otro).getOffs() == this.offs;
    }

    @Override
    public int size(){
        return Integer.parseInt(off.toString())*subtipo.size();
    }
}
