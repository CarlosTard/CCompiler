package ast;
import java.util.ArrayList;
import asem.PilaTablas;
import errors.GestionErroresExp;

public class EList extends E{
    private ArrayList<E> lista;
    public EList(){
        lista = new ArrayList<E>();
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for(int i = 0; i < lista.size(); ++i){
            sb.append(lista.get(i).toString());
            if(i < lista.size()-1)
                sb.append(", ");
        }
        sb.append("}");
        return sb.toString();
    }
    public void add(E e){
        lista.add(e);
    }
    
    public ArrayList<E> get(){
        return lista;
    }
    
    public TipoE id() {return TipoE.LIST;}
    
    public void vincula(PilaTablas pt){
        for(E e: lista)
            e.vincula(pt);
    }
    
    public boolean chequea(){ // Hay que crear el array. Hay que chequear que tengan las mimsas dimensiones bien
        boolean ok = true;
        if(lista.size() == 0)
            return true;
        ArrayList<E> listaAux = new ArrayList<E>(lista);
        E first = listaAux.get(0);
        if(!first.chequea()){
            ok = false;
        }
        T type1 = first.tipo;
        listaAux.remove(0);
        for(E e : listaAux){
            if(!e.chequea()){
                ok = false;
            }
            T type2 = e.tipo;
            if(type1.id() != type2.id()){
                GestionErroresExp.errorSem(this.ulex,"Elementos de distinto tipo en EList(): " + type1.id() + ", " + type2.id());
                ok = false;
            }
            type1 = type2;
        }
        this.tipo = new TArray(type1);
        return ok;
    }
}

