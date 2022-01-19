package ast;
import java.util.ArrayList;
import asem.PilaTablas;
import errors.GestionErroresExp;

public class SPrint extends S{ 
    private String s = null;
    private E e = null;
    
    public SPrint(String s){
        super(TipoS.PRINT);
        this.s = s;
    }
    public SPrint(E e){
        super(TipoS.PRINT);
        this.e = e;
    }
    public E getE(){
        return e;
    }
    
    public String toString(int h){
        StringBuilder sb = new StringBuilder();
        String tabs = getTabs(h);
        if(s != null)
            sb.append(tabs +"|print(\"" + s + "\")");
        else if(e != null)
            sb.append(tabs +"|print(" + e + ")");
        return sb.toString();
    }
    public void vincula(PilaTablas pt){
        if(this.e != null) e.vincula(pt);
    }
    
    public boolean chequea(){
        if(e != null){
            if(!e.chequea()){
                return false;
            }
            if(e.tipo.id() != TipoT.INT && e.tipo.id() != TipoT.FLOAT && e.tipo.id() != TipoT.PUNTERO){
                GestionErroresExp.errorSem(this.ulex,"Chequeo de tipos fallido. Intentando hacer print sobre variable de tipo: " + e.tipo.id());
                return false;
            }
        }
        
        return true;
    }
}
