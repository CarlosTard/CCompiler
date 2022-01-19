package ast;
import asem.PilaTablas;
import java.util.ArrayList;
import errors.GestionErroresExp;

public class TFuncion extends T {
    private T ret;
    public SFunction link = null;
    private ArrayList<T> argTypes;
    public TFuncion(T sub, ArrayList<S> args, SFunction Link){
        super(TipoT.FUNCION);
        this.ret = sub;
        argTypes = new ArrayList<T>();
        for(S s: args)
            argTypes.add(s.tipo);
        this.link = link;
    }

    public TFuncion(EIden sub, ArrayList<T> types){
        super(TipoT.FUNCION);
        this.ret = ((TFuncion) sub.tipo).getRet();
        argTypes = types;
    }
    
    public T getRet(){
        return ret;
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append( this.ret + " funcion" + "(");
        for(int i = 0; i < argTypes.size(); i++){
            sb.append(argTypes.get(i).toString());
            if(i < argTypes.size()-1)
                sb.append(",");
        }
        sb.append(")");
        
        return sb.toString();
    }
    public T vinculaT(PilaTablas pt){
         throw new UnsupportedOperationException("Calling vinculaT on TFuncion");
    }
    public ArrayList<T> getArgTypes(){
        return argTypes;
    }
    
     public boolean chequea(){
         throw new UnsupportedOperationException("Calling chequea on TFuncion"); 
    }
     public boolean mismoTipo(T otr){
        if(otr.id() != TipoT.FUNCION )
            return false;
        TFuncion otro = (TFuncion) otr;
        int i = 0;
        if(otro.getArgTypes().size() != argTypes.size()){
            GestionErroresExp.errorSem(this.ulex,"Numero de argumentos incorrecto");
            return false;
        }
        boolean ok = true;
        for(T t: argTypes){
            if(!t.mismoTipo(otro.getArgTypes().get(i))){
                GestionErroresExp.errorSem(this.ulex,"Tipo de argumento incorrecto en argumento " + i + " ésimo");
                ok = false;
            }
            ++i;
        }
        if(ret != otro.getRet()){
            GestionErroresExp.errorSem(this.ulex,"Tipos de return distintos: " + ret + ", " + otro.getRet());
            ok = false;
        }
        
        return ok;
    }

    @Override
    public int size(){
        int suma = 0;
        for(T t : argTypes){
            suma = suma + t.size();
        }
        return suma;//+link.size(link,0,0);
    } 
}
