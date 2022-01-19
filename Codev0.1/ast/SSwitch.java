package ast;
import java.util.ArrayList;
import asem.PilaTablas;
import errors.GestionErroresExp;

public class SSwitch extends S{
    private E var;
    private ArrayList<S> cases;
    private S def = null;
    
    public SSwitch(E var, ArrayList<S> cases){
        super(TipoS.SWITCH);
        this.var = var;
        this.cases = cases;
    }
    public SSwitch(E var, ArrayList<S> cases, S def){
        super(TipoS.SWITCH);
        this.var = var;   
        this.cases = cases;
        this.def = def;
    }
    
    public String toString(int h){
        StringBuilder sb = new StringBuilder();
        String tabs = getTabs(h);
        sb.append(tabs +"|switch(" + var + "){\n");
        for(S c : cases){
            sb.append(c.toString(h+1));
        }
        sb.append("\n" );
        if(def != null){
            sb.append(getTabs(h+1) + "default: \n");
            sb.append(def.toString(h+2));
            sb.append("\n");
        }
        
        sb.append(tabs + " }");
        return sb.toString();
    }
    public E getVar(){
        return var;
    }
    public ArrayList<S> getCases(){
        return cases;
    }
    public S getDef(){
        return def;
    }
    public void vincula(PilaTablas pt){
        var.vincula(pt);
        for(S s: cases)
            s.vincula(pt);
        if(def != null)
            def.vincula(pt);
    }
    
    public boolean chequea(){
        boolean ok = var.chequea();
        for(S s : cases){
            if(!s.chequea() ){// comprobamos que los cases sean correctos y que el tipo coincida con la variable (var) del switch
                ok = false;
            }else if(!var.tipo.mismoTipo(s.tipo)){
                        System.out.println(var.tipo.id());
                GestionErroresExp.errorSem(s.getUL(),"El tipo del switch: " + var.tipo + " no coincide con el tipo de algun case: " + s.tipo);
                ok = false;
            }
        }
        if(def != null && !def.chequea()){
            ok = false;
        }

        return ok;
    }
}
