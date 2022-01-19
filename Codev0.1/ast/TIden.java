package ast;
import asem.PilaTablas;
import errors.GestionErroresExp;

public class TIden extends T {
    private String iden;
    
    public TIden(String id){
        super(TipoT.IDEN);
        this.iden = id;
        //this.setUL(id.getUL());
    }
    
    /*public TIden(String id, S link){
        super(TipoT.IDEN);
        this.iden = id;
        this.link = link;
    }*/
    public String toString(){
        return iden.toString();
    }

    public T vinculaT(PilaTablas pt){
        S s = pt.buscaId(iden, this.ulex);
        this.link = s;
        if(s != null && (s.id() == TipoS.USING || s.id() == TipoS.ENUM )){
            //SUsing ss = (SUsing) s;

            return s.tipo; // Este return produce que el objeto "this" se sustituya por el objeto del tipo del Using.
        }          // es decir, en el nodo de arriba se cambia este hijo por el subarbol que nos proporcione el using
        return this;
    }
    public boolean chequea(){
        if(this.link.id() != TipoS.ENUM && this.link.id() != TipoS.CLASS){ //puede vincular con: enum, clase, using NO porque teoricamente ya estan preprocesados.
            GestionErroresExp.errorSem(this.ulex,"Chequeo de tipos fallido. Identificador de tipo apuntando a: " + this.link.id());
                return false;
        } 
        return true; 
    }
    
    public boolean mismoTipo(T otro){
        return otro.id() == TipoT.IDEN && this.link == otro.link ;
    }
    
      @Override
    public int size(){
        return this.link.tipo.size();
    }

}
