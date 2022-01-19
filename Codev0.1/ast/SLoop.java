package ast;
import java.util.ArrayList;
import asem.PilaTablas;
import errors.GestionErroresExp;

public class SLoop extends S{
    private S dec = null;
    private E condAv;
    private S incremento = null;
    private SList instructs;
    
    public SLoop(E cond, S instructs){
        super(TipoS.WHILE);
        this.condAv = cond;
        if(instructs.id() != TipoS.LIST){
            this.instructs = new SList();
            this.instructs.add(instructs);
        }else
            this.instructs = (SList) instructs;
    }
    public SLoop(S dec, E condAv, S incremento, S instructs){
        super(TipoS.FOR);
        this.dec = dec;
        this.condAv = condAv;
        this.incremento = incremento;
        if(instructs.id() != TipoS.LIST){
            this.instructs = new SList();
            this.instructs.add(instructs);
        }else
            this.instructs = (SList) instructs;
    }
    
    public String toString(int h){
        StringBuilder sb = new StringBuilder();
        String tabs = getTabs(h);
        sb.append(tabs);
        if(this.id() == TipoS.FOR){
            sb.append("|for(" + dec + " " +  condAv + "; " + incremento + ")\n");
        }
        else{
            sb.append("|while("+ condAv +"){\n");
        }
        sb.append(instructs.toString(h+1));
        sb.append("\n" + tabs + "}");
        return sb.toString();
    }
    public S getDec(){
        return dec;
    }
    public E getCond(){
        return condAv;
    }
    
    public S getInc(){
        return incremento;
    }
    public S getInstructs(){
        return instructs;
    }
    
    public void vincula(PilaTablas pt){
         if(this.id() == TipoS.FOR){
            dec.vincula(pt);
            condAv.vincula(pt);
            incremento.vincula(pt);
        }
        else{
            condAv.vincula(pt);
        }
        pt.abreBloque();
        instructs.vincula(pt);
        pt.cierraBloque();
    }
    
    public boolean chequea(){
        boolean ok = true;
        if(!condAv.chequea()){
            ok = false;
        }
        if(dec != null && !dec.chequea()){
            ok = false;
        }
        if(condAv != null && !condAv.chequea()){
            ok = false;
        }
        if(!instructs.chequea()){
            ok = false;
        }
        if(condAv.tipo.id() != TipoT.BOOL){
            GestionErroresExp.errorSem(this.ulex,"Chequeo de tipos fallido. Expresión " + condAv + " no booleana.");
            return false;
        }
    
        return ok;
    }
}

