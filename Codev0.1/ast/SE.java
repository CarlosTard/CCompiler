package ast;
import java.util.ArrayList;
import asem.PilaTablas;
import errors.GestionErroresExp;

public class SE extends S{  //para poder poner a.c().x(); o ++i;
    private E e0 = null;
    
    public SE(E e0){
        super(TipoS.E);
        this.e0 = e0;
    }
    
    public String toString(int h){
        StringBuilder sb = new StringBuilder();
        String tabs = getTabs(h);
        sb.append(tabs +"|");
        if(e0 != null)
            sb.append(e0.toString());
        return sb.toString();
    }
    public E getE(){
        return e0;
    }
    
    public void vincula(PilaTablas pt){
        e0.vincula(pt);
    }
    
    public boolean chequea(){
        if(!e0.chequea()){
            return false;
        }
        this.tipo = e0.tipo;
        return true;
    }

}
