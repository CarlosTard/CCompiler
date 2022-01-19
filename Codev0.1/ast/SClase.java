
package ast;
import asem.PilaTablas;
import java.util.ArrayList;
import errors.GestionErroresExp;
import java.util.HashMap;

public class SClase extends S{
    private EIden iden;
    private ArrayList<S> attrList;
    private ArrayList<S> functionList = null;
    private HashMap<String, SDecl> attr;
    private HashMap<String,SFunction> funct;
    
    public SClase(E iden, ArrayList<S> attrList, ArrayList<S> functionList){
        super(TipoS.CLASS);
        this.iden = (EIden) iden;
        this.attrList = attrList;
        this.functionList = functionList;
        this.attr = new HashMap<String, SDecl>();
        this.funct = new HashMap<String,SFunction>();
        for(S s:this.attrList){
           attr.put(((SDecl) s).iden().iden(), (SDecl) s);
           s.setEstoyDentroDeClase();
        }
        for(S s:this.functionList){
            SFunction sf = (SFunction) s;
            sf.funcName = this.iden.iden() + "_" + sf.funcName; // Nombre de metodo de clase es NombreClase_nombreFuncion
            sf.setEstoyDentroDeClase();
            funct.put(sf.iden().iden(), (SFunction) s);
        }
    }
    public SClase(E iden, ArrayList<S> attrList){
        super(TipoS.CLASS);
        this.iden = (EIden) iden;
        this.attrList = attrList;
        this.attr = new HashMap<String, SDecl>();
        for(S s:this.attrList){
           attr.put(((SDecl) s).iden().iden(), (SDecl) s);
           s.setEstoyDentroDeClase();
        }
    }
    
    public String toString(int h){
        StringBuilder sb = new StringBuilder();
        String tabs = getTabs(h);
        sb.append(tabs +"|class " + iden + "{\n");
        for(S s:attrList){
            sb.append(s.toString(h+1));
            sb.append("\n");
        }
        
        if(functionList != null){
           for(S s:functionList){
                sb.append(s.toString(h+1));
                sb.append("\n");
            }
        }
        
        sb.append(tabs + " }");
        return sb.toString();
    }
    public E getIden(){
        return iden;
    }
    public S search(String nombre){
        S s = attr.get(nombre);
        if(s == null && this.funct != null)
            s = funct.get(nombre);
       return s;
    }
    public ArrayList<S> getAttrList(){
        return attrList;
    }
    public ArrayList<S> getFunctionList(){
        return functionList;
    }
    
    public void vincula(PilaTablas pt){
        pt.insertaId(iden.iden(), this, this.ulex);
        pt.abreBloque();
        for(S s : attrList)
            s.vincula(pt);
        if(functionList != null)
            for(S s : functionList)
                s.vincula(pt);
        pt.cierraBloque();
    }
    
    public boolean chequea(){
        boolean ok = true;
        ArrayList<T> attrTypes = new ArrayList<T>();
        ArrayList<T> funcTypes = new ArrayList<T>();
        this.tipo = new TClase(attrTypes,funcTypes);
        
        for(S s : attrList){
            if(!s.chequea()){
                ok = false;
            }
            attrTypes.add(s.tipo);
        }
        if(functionList != null){
            for(S s : functionList){
                if(!s.chequea())
                    ok = false;
                funcTypes.add(s.tipo);
            }
        }
        return ok;
    }
}
