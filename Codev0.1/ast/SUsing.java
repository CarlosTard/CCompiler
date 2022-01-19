package ast;
import java.util.ArrayList;
import asem.PilaTablas;
import errors.GestionErroresExp;

public class SUsing extends S{
    private EIden iden;
    
    public SUsing(E iden, T type){
        super(TipoS.USING);
        this.iden = (EIden) iden;
        this.tipo = type;
    }
    
    public String toString(int h){
        StringBuilder sb = new StringBuilder();
        String tabs = getTabs(h);
        sb.append(tabs +"|using " + iden + " = " + tipo);
        return sb.toString();
    }

    public void vincula(PilaTablas pt){
         this.tipo = tipo.vinculaT(pt);
         pt.insertaId(iden.iden(), this, this.ulex);
    }
    public boolean chequea(){
        return this.tipo.chequea();
    }
}
