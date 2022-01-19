package ast;
import java.util.ArrayList;
import asem.PilaTablas;
import errors.GestionErroresExp;

public class ENew extends E{

    public ENew(T type){
        this.tipo = type;
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("new " + tipo.toString());
        return sb.toString();
    }
    public TipoE id() {return TipoE.NEW;}
    
    public void vincula(PilaTablas pt){
        this.tipo = tipo.vinculaT(pt);
    }
    
    public boolean chequea(){
        return this.tipo.chequea();
    }

}

