package ast;
import java.util.ArrayList;
import asem.PilaTablas;
import errors.GestionErroresExp;

public class SToken extends S{ //para continue y break

    public SToken(TipoS tipo){
        super(tipo);
    }
    
    public String toString(int h){
        StringBuilder sb = new StringBuilder();
        String tabs = getTabs(h);
        sb.append(tabs +"|" + id().name());
        return sb.toString();
    }
    public void vincula(PilaTablas pt){
    }
    public boolean chequea(){
        return true;
    }

}
