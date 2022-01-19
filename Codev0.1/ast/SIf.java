package ast;
import java.util.ArrayList;
import asem.PilaTablas;
import errors.GestionErroresExp;

public class SIf extends S{
    private E cond;
    private S then;
    private S elsee = null;
    
    public SIf(E cond, S then){
        super(TipoS.IF);
        this.cond = cond;
        this.then = then;
    }
    public SIf(E cond, S then, S elsee){
        super(TipoS.IF);
        this.cond = cond;   
        this.then = then;
        this.elsee = elsee;
    }
    
    public String toString(int h){
        StringBuilder sb = new StringBuilder();
        String tabs = getTabs(h);
        sb.append(tabs +"|if(" + cond + "){\n");
        if(then != null)
            sb.append(then.toString(h+1));
        else 
            sb.append(tabs + "    |nullllll");
        sb.append("\n" );
        if(elsee != null){
            sb.append(tabs + " }else{\n");
            sb.append(elsee.toString(h+1));
            sb.append("\n" );
        }
        
        sb.append(tabs + " }");
        return sb.toString();
    }
    public E getCond(){
        return cond;
    }
    public S getThen(){
        return then;
    }
    public S getElse(){
        return elsee;
    }
    
    public void vincula(PilaTablas pt){
        cond.vincula(pt);
        pt.abreBloque();
        then.vincula(pt);
        pt.cierraBloque();
        pt.abreBloque();
        if(elsee != null)
            elsee.vincula(pt);
        pt.cierraBloque();
    }
    
    public boolean chequea(){
        boolean ok = true;
        if(!cond.chequea()){
            ok = false;
        }
        if(!then.chequea())
            ok = false;
        if(elsee != null && !elsee.chequea()){
             ok = false;
        }
        if(ok && cond.tipo.id() != TipoT.BOOL){
            GestionErroresExp.errorSem(this.ulex,"Chequeo de tipos fallido. Expresión " + cond + " no booleana.");
            return false;
        }
        
        return ok;
    }
}
