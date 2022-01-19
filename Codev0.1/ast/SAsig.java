package ast;
import java.util.ArrayList;
import asem.PilaTablas;
import errors.GestionErroresExp;

public class SAsig extends S{
    private E ladoIz;
    private E ladoDer;
    
    public SAsig(E ladoIz, E ladoDer){
        super(TipoS.ASIG);
        this.ladoIz = ladoIz;
        this.ladoDer = ladoDer;
    }
    public E ladoDer(){
        return ladoDer;
    }
    public E ladoIz(){
        return ladoIz;
    }
    
    public String toString(int h){
        StringBuilder sb = new StringBuilder();
        String tabs = getTabs(h);
        if(ladoIz == null)
            sb.append(tabs +"|;");
        else
            sb.append(tabs +"|" + ladoIz + " = " + ladoDer);

        return sb.toString();
    }
    
    public void vincula(PilaTablas pt){
        if(ladoIz != null){
            ladoIz.vincula(pt);
            ladoDer.vincula(pt);
        }
    }
    
    public boolean chequea(){
        if(ladoIz == null){
            GestionErroresExp.errorSem(this.ulex,"Chequeo de tipos fallido. Lado izquierdo de la asignación vacío");
            return false;
        }
        boolean ok = ladoIz.chequea();
        if(!(ladoDer.chequea())){
            ok = false;
        }
        if(!ladoIz.tipo.mismoTipo(ladoDer.tipo)){
            
            GestionErroresExp.errorSem(this.ulex,"Los tipos no coinciden: " + ladoIz.tipo.toString() + ", " + ladoDer.tipo.toString());
            return false;
        }
        return ok;

    }
   
}
