package ast;
import java.util.ArrayList;
import asem.PilaTablas;
import errors.GestionErroresExp;

public class SError extends S{
    
    public SError(){
        super(TipoS.ERROR);
    }
    
    public String toString(int h){
        StringBuilder sb = new StringBuilder();
        String tabs = getTabs(h);
        sb.append(tabs +"|KUT << ERROR;");
        return sb.toString();
    }
    
    public void vincula(PilaTablas pt){
        throw new UnsupportedOperationException("Calling vincula on SError");
    }
    
    public boolean chequea(){
        throw new UnsupportedOperationException("Calling chequea on SError");
    }
}
