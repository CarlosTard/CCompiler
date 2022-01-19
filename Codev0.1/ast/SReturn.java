package ast;
import java.util.ArrayList;
import asem.PilaTablas;
import errors.GestionErroresExp;

public class SReturn extends S{ 
    private E e0 = null;
    
    public SReturn(E e0){
        super(TipoS.RETURN);
        this.e0 = e0;
    }
    public SReturn(){
        super(TipoS.RETURN);
    }
    
    public String toString(int h){
        StringBuilder sb = new StringBuilder();
        String tabs = getTabs(h);
        sb.append(tabs +"|return");
        if(e0 != null)
            sb.append(" " + e0.toString());
            sb.append(";");
        return sb.toString();
    }
    public E getE(){
        return e0;
    }
    public void vincula(PilaTablas pt){
        if(this.e0 != null)
            e0.vincula(pt);
    }
    public boolean chequea(){
        if(e0 != null){
            if(!e0.chequea()){
                return false;
            }
            this.tipo = e0.tipo; // Hay que ver que concuerde con el tipo de la funcion
        }
        return true;
    }
}
