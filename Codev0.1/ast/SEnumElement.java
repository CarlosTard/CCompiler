package ast;
import java.util.ArrayList;
import asem.PilaTablas;
import java.util.Collections;
import errors.GestionErroresExp;

public class SEnumElement extends S{ // Clase auxiliar, para vincular los elementos enumerados a su entero correspondiente
    private EIden iden;
    private int i;
    private SEnum enun;
    
    public SEnumElement(E iden, int i, SEnum enun){
        super(TipoS.ENUMELEMENT);
        this.iden = (EIden) iden;
        this.i = i;
        this.enun = enun;
    }
    
    public String toString(int h){
        return iden.toString();
    }
    
    public E getIden(){
        return iden;
    }
    
    public E getVal(){
        return iden;
    }
    public void vincula(PilaTablas pt){
        this.tipo = new TIden(enun.getIden().iden());
        this.setUL(this.iden.getUL());
        this.tipo.setUL(this.iden.getUL());

        this.tipo = tipo.vinculaT(pt);
        
            
        pt.insertaId(iden.iden(), this, this.ulex);
    }
    
    public boolean chequea(){
        this.tipo.chequea();
        this.tipo.setUL(iden.getUL());
        return true;
    }

}

