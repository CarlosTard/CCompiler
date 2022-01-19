package ast;
import java.util.ArrayList;
import asem.PilaTablas;
import errors.GestionErroresExp;

public class SCase extends S{
    private E var;
    private S block;
    
    public SCase(E var, S block){
        super(TipoS.CASE);
        this.var = var;
        this.block = block;
    }
    
    public String toString(int h){
        StringBuilder sb = new StringBuilder();
        String tabs = getTabs(h);
        sb.append(tabs +"|case " + var + ": ");
        sb.append("\n");
        sb.append(block.toString(h+1));
        sb.append("\n");
        return sb.toString();
    }
    public E getVar(){
        return var;
    }
    public S getBlock(){
        return block;
    }
    
    public void vincula(PilaTablas pt){
        var.vincula(pt);
        block.vincula(pt);
    }
    
    public boolean chequea(){
        boolean ok = var.chequea(); 

        if(ok  && var.tipo.id() != TipoT.INT && var.tipo.id() != TipoT.ENUM){
            GestionErroresExp.errorSem(this.ulex, " " + var.tipo.id());
            GestionErroresExp.errorSem(this.ulex,"Tipo de la variable no es apta para el case: " + var.tipo);
            ok = false;
        }
        if(!block.chequea()){
            ok =  false;
        }
        this.tipo = var.tipo;
        return ok;
    }
}
