package ast;
import java.util.ArrayList;
import asem.PilaTablas;
import errors.GestionErroresExp;

public class STipoIden extends S{
    private EIden iden;
    
    public STipoIden(T tipo, EIden iden){
        super(TipoS.STIPOIDEN);
        this.tipo = tipo;
        this.iden = iden;
    }
    
    public T type(){
        return tipo;
    }
    
    public EIden iden(){
        return iden;
    }
    
    public String toString(int h){
        StringBuilder sb = new StringBuilder();
        String tabs = getTabs(h);
        sb.append(this.tipo + " " + this.iden);

        return sb.toString();
    }
    
     public void vincula(PilaTablas pt){
        this.tipo = tipo.vinculaT(pt);
        pt.insertaId(iden.iden(), this, this.ulex);
    }
    public boolean chequea(){
        boolean ok = tipo.chequea();
         if(tipo.id() == TipoT.VOID){
             GestionErroresExp.errorSem(this.ulex,"Chequeo de tipos fallido. Argumento de tipo VOID");
                return false;
         }
         return ok; 
    }
}
