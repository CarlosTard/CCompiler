package ast;
import java.util.ArrayList;
import java.util.Collections;
import asem.PilaTablas;
import errors.GestionErroresExp;

public class EBin extends E {
    private TipoE id;
    private E opnd1;
    private E opnd2;
    public  EIden idenn;
    public EBin(E opnd1, E opnd2, TipoE id) {
        this.id = id;
        this.opnd1 = opnd1;
        this.opnd2 = opnd2;
    }

    public E opnd1() {
        return opnd1;
    }
    
    public E opnd2() {
        return opnd2;
    }
    
    public TipoE id() {
        return id;
    }
    
    public String toString() {
        return id.name()+"("+opnd1().toString()+","+opnd2().toString()+")";
    }
    
    public void vincula(PilaTablas pt){
        opnd1.vincula(pt);
        if(id == TipoE.INSTANCE){
            // y mi pobre cabecita explotó
        }
        else opnd2.vincula(pt);
    }
    
    
    public boolean chequea(){

        if(opnd2 == null)
            return false;

        boolean ok = opnd1.chequea();
        
        T type1 = opnd1.tipo;
        T type2 = null;

        if(id == TipoE.FUNCTION){
            ArrayList<E> list = ((EList) opnd2).get();
            ArrayList<T> linkArgs = new ArrayList<T>();

            for(E e: list){
                boolean ok2 = e.chequea();
                ok = ok && ok2;
                linkArgs.add(e.tipo);
            }
            if(type1.id() != TipoT.FUNCION){
                GestionErroresExp.errorSem(this.ulex,"Error en la operación binaria: " + id.name() + ". " + opnd1.toString() + " no es una función.");
                this.tipo = type1;
                return false;
            }
            TFuncion comp;
            if(opnd1.id() == TipoE.IDEN)
                comp = new TFuncion((EIden) opnd1,linkArgs);
            else
                comp = new TFuncion(((EBin) opnd1).idenn,linkArgs);
            comp.setUL(this.ulex);
            this.tipo = comp.getRet();
            if(!comp.mismoTipo(type1))
                return false;
            
            return ok;
        }
    
        if(id == TipoE.INSTANCE){ //operador punto
            if(!ok)
                return ok; //es esencial que lo de la izquierda del punto haya chequeado bien
            if(opnd1.tipo.link == null){
                GestionErroresExp.errorSem(this.ulex,"Error en la operación punto. El primer operando no es un objeto: " + opnd1.toString());
                this.tipo = opnd1.tipo;
                return false;
            }
            type1 = opnd1.tipo.link.tipo;
            
            if(type1.id() != TipoT.CLASE){
                GestionErroresExp.errorSem(this.ulex,"Error en la operación punto. El primer operando no es un objeto: " + opnd1.toString() + " tiene tipo " + type1.id() + " vincula con " + opnd1.link);
                this.tipo = opnd1.tipo;
                return false;
            }
            if(opnd2.id() != TipoE.IDEN){
                GestionErroresExp.errorSem(this.ulex,"Error en la operación punto. El segundo operando no es un identificador: " + opnd2.toString());
                this.tipo = type1;
                return false;
            }
            SClase clase = (SClase) opnd1.tipo.link;
            idenn = (EIden) opnd2;
            String atributo = idenn.iden();
            S definicion = clase.search(atributo);
            if(definicion == null){
                GestionErroresExp.errorSem(this.ulex,"Error en la operación punto. No se ha encontrado definicion de atributo: " + atributo + "en la clase: " + clase.getIden());
                this.tipo = type1;
                return false;
            }
            this.link = definicion; // en el punto, hay que hacer un tratamiento especial
            this.tipo = definicion.tipo;
            idenn.link = definicion;
            idenn.tipo = definicion.tipo;

            return ok;

        }
        if(id == TipoE.SUBINDICE){
            boolean ok2 = opnd2.chequea();
            ok = ok && ok2;
            if(opnd1.tipo.id() != TipoT.ARRAY){
                GestionErroresExp.errorSem(this.ulex,"Error en la operación binaria: " + id.name() + ". El primer operando no es un array: " + opnd1.toString());
                this.tipo = opnd1.tipo;
                ok = false;
            }
            this.tipo = opnd1.tipo.getSub();
            if(opnd2.tipo.id() != TipoT.INT){
                GestionErroresExp.errorSem(this.ulex,"Error en la operación binaria: " + id.name() + ". El offset no es un entero: " + opnd2.toString());
               ok = false;
            }
            // No me gusta :(
            return ok;
        }
        boolean ok2 = opnd2.chequea();
        ok = ok && ok2;
        type2 = opnd2.tipo;
        
        if(id == TipoE.SUMA||id == TipoE.RESTA||id == TipoE.MUL||id == TipoE.DIV||id == TipoE.POW||id == TipoE.MOD || id ==  TipoE.MENOR || 
            id ==  TipoE.MENORIGUAL || id ==  TipoE.MAYOR || id ==  TipoE.MAYORIGUAL){
            if((type1.id() != TipoT.INT) && (type1.id() != TipoT.FLOAT)){
                GestionErroresExp.errorSem(this.ulex,"Error en la operación binaria: " + id.name() + ". El primer operando no es un número: " + opnd1.toString());
                this.tipo = type1;
                return false;
            }

            if((type2.id() != TipoT.INT) && (type2.id() != TipoT.FLOAT)){
                GestionErroresExp.errorSem(this.ulex,"Error en la operación binaria: " + id.name() + ". El segundo operando no es un número: " + opnd2.toString());
                this.tipo = type2;
                return false;
            }
            if((type1.id() == TipoT.FLOAT)||(type2.id() == TipoT.FLOAT))
                this.tipo = new TBasic(TipoT.FLOAT);
            else this.tipo = new TBasic(TipoT.INT);
            
            if(id ==  TipoE.MENOR || 
            id ==  TipoE.MENORIGUAL || id ==  TipoE.MAYOR || id ==  TipoE.MAYORIGUAL)
                this.tipo = new TBasic(TipoT.BOOL);
            return ok;
        }
        
        if(id == TipoE.AND||id == TipoE.OR){
            if((type1.id() != TipoT.BOOL)||(type2.id() != TipoT.BOOL)){
                GestionErroresExp.errorSem(this.ulex,"Error en la operación binaria: " + id.name() + ". Operandos no booleanos");
                ok = false;
            }
            this.tipo = type1;
            return ok;
        }
        this.tipo = new TBasic(TipoT.BOOL);
        if(!type1.mismoTipo(type2)){
            GestionErroresExp.errorSem(this.ulex,"Error en la operación binaria: " + id.name() + ". El tipo de los operandos no coincide: " + opnd1.toString() + ", " + opnd2.toString());
            
            return false;
        }
        return ok;
    }

}
