package ast;
import java.util.ArrayList;
import asem.PilaTablas;
import errors.GestionErroresExp;

public class SPrunt extends S{ 
    private EIden i ;
    
    public SPrunt(EIden i){
        super(TipoS.PRUNT);
        this.i = i;
    }
    
    public EIden getIden(){
        return i;
    }
    
    public String toString(int h){
        StringBuilder sb = new StringBuilder();
        String tabs = getTabs(h);
        sb.append(tabs +"|prunt(" + i + ")");
        return sb.toString();
    }
    
    public void vincula(PilaTablas pt){
         this.i.vincula(pt);
    }
    
    public boolean chequea(){
        if(!i.chequea()){
            return false;
        }
        this.tipo = i.tipo;
        if(this.tipo.id() != TipoT.INT && this.tipo.id() != TipoT.FLOAT){
            GestionErroresExp.errorSem(this.ulex,"Chequeo de tipos fallido. Intentando hacer prunt sobre variable de tipo: " + this.tipo.id());
            return false;
        }
        return true;
    }

}
