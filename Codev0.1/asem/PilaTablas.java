
package asem;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.ArrayDeque;
import ast.S;
import alex.UnidadLexica;
import errors.GestionErroresExp;

public class PilaTablas{
    //private ArrayList<HashMap<String,S>> pilaTablas;
    private ArrayDeque<HashMap<String,S>> pilaTablas;
    public boolean hayError = false; //poner a true cuando haya error
    
    public PilaTablas(){
        pilaTablas = new ArrayDeque<HashMap<String, S>>();
    }
    
    public void abreBloque(){
        HashMap<String, S> ambito = new HashMap<String, S>();
        pilaTablas.push(ambito);
    }
    
    public void cierraBloque(){
        pilaTablas.pop();
    }
    
    public void insertaId(String id, S puntero, UnidadLexica ul){
        S punt = null;
        for(HashMap<String,S> hm: pilaTablas){ //los recorre en el orden que queremos
            punt = hm.get(id);
            if(punt != null)    
                break;
        }
        if(punt != null)
            GestionErroresExp.warningSem(ul,("hay varios identificadores llamados: " + id ));
        HashMap<String, S> curr = pilaTablas.getFirst();
        curr.put(id,puntero);
    }
    
    public S buscaId(String id, UnidadLexica ul){ // Pasamos la UL para que los errores sean mas expresivos
        S punt = null;
        for(HashMap<String,S> hm: pilaTablas){ //los recorre en el orden que queremos
            punt = hm.get(id);
            if(punt != null)    
                break;
        }
        if(punt == null){
            hayError = true;
            GestionErroresExp.errorSem(ul,"No encontrado identificador: " + id );
        }
        return punt;
    }
}

