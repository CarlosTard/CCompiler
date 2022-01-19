package ast;
import asem.PilaTablas;
import java.util.ArrayList;
import errors.GestionErroresExp;

public class TClase extends T {
    private ArrayList<T> attrTypes;
    private ArrayList<T> funcTypes;
    public TClase(ArrayList<T> attrTypes, ArrayList<T> funcTypes){
        super(TipoT.CLASE);
        this.attrTypes = attrTypes;
        this.funcTypes = funcTypes;
    }
    public ArrayList<T> getAttrTypes(){
        return attrTypes;
    }
    public ArrayList<T> getFuncTypes(){
        return funcTypes;
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Clase" + "{(");
        for(int i = 0; i < attrTypes.size(); i++){
            sb.append(attrTypes.get(i).toString());
            if(i < attrTypes.size()-1)
                sb.append(",");
        }
        sb.append("),(");
        for(int i = 0; i < funcTypes.size(); i++){
            sb.append(funcTypes.get(i).toString());
            if(i < funcTypes.size()-1)
                sb.append(",");
        }
        sb.append(")}");
        
        return sb.toString();
    }
    public T vinculaT(PilaTablas pt){
        throw new UnsupportedOperationException("Calling vinculaT on TClase");
    }
    
    public boolean chequea(){
        throw new UnsupportedOperationException("Calling chequea on TClase");
    }
    
    public boolean mismoTipo(T otr){
        if(otr.id() != TipoT.CLASE)
            return false;
        TClase otro = (TClase) otr;
        int i = 0;
        boolean ok = true;
        if(otro.getAttrTypes().size() != attrTypes.size()){
            GestionErroresExp.errorSem(this.ulex,"Numero de atributos incorrecto");
            return false;
        }
        
        if(otro.getFuncTypes().size() != funcTypes.size()){
            GestionErroresExp.errorSem(this.ulex,"Numero de métodos incorrecto");
            return false;
        }
        for(T t: attrTypes){
            if(!t.mismoTipo(otro.getAttrTypes().get(i))){
                GestionErroresExp.errorSem(this.ulex,"Tipo de atributo incorrecto");
                ok = false;
            }
            ++i;
        }
        i = 0;
        for(T t: funcTypes){
            if(!t.mismoTipo(otro.getFuncTypes().get(i))){
                GestionErroresExp.errorSem(this.ulex,"Tipo de método incorrecto");
                ok = false;
            }
            ++i;
        }
        
        
        return ok;
    }

    @Override
    public int size(){
        int suma = 0;
        for(T t: attrTypes){
            suma = suma + t.size();
        }
        return suma;
    }
}

