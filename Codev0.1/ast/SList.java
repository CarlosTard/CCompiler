package ast;
import java.util.ArrayList;
import asem.PilaTablas;
import errors.GestionErroresExp;

public class SList extends S{ 
    private ArrayList<S> lista;
    public SList(){
        super(TipoS.LIST);
        lista = new ArrayList<S>();
    }
    public String toString(int h){
        StringBuilder sb = new StringBuilder();
        String tabs = getTabs(h);
        sb.append(tabs + "|{\n");
        for(S s: lista){
            if(s == null)
                sb.append(tabs + "    |nullllll");
            else
                sb.append(s.toString(h+1));
            sb.append("\n" );
        }
        sb.append(tabs + " }" );
        return sb.toString();
    }
    public void add(S e){
        lista.add(e);
    }
    
    public void addAll(ArrayList<S> second){
        lista.addAll(second);
    }
    
    public ArrayList<S> get(){
        return lista;
    }
    
    public void vincula(PilaTablas pt){
        pt.abreBloque();
        for(S s : lista)
            s.vincula(pt);
        pt.cierraBloque();
    }
    
    public boolean chequea(){
        boolean ok = true;
        for(S s : lista){
            if(!s.chequea()){
                ok = false;
            }
        }
        return ok;
    }
   
}
