package ast;
import asem.PilaTablas;
import errors.GestionErroresExp;

public class EUn extends E {
    private TipoE id;
    private E opnd1;
    public EUn(E opnd1, TipoE id) {
        this.id = id;
        this.opnd1 = opnd1;
    }
    
    public E opnd1() {
        return opnd1;
    }

    
    public TipoE id() {
        return id;
    }
    
    public String toString() {
        return id.name()+"("+opnd1().toString()+")";
    }
    public void vincula(PilaTablas pt){
        opnd1.vincula(pt);
    }
    
    public boolean chequea(){
        boolean ok = true;
        if(!opnd1.chequea())
            ok = false;
        T type1 = opnd1.tipo;
        this.tipo = type1;
            
        if((id == TipoE.OPUESTO||id == TipoE.MASMAS||id == TipoE.MENOSMENOS) && (type1.id() != TipoT.INT) && (type1.id() != TipoT.FLOAT)){
            GestionErroresExp.errorSem(this.ulex,"Error en la operación unaria: " + id.name() + ". El operando no es un número: " + opnd1.toString());
            return false;
        }
        if((id == TipoE.NOT) && (type1.id() != TipoT.BOOL)){
            GestionErroresExp.errorSem(this.ulex,"Error en la operación unaria: " + id.name() + ". El operando no es booleano: " + opnd1.toString());
            return false;
        }
        if((id == TipoE.DEREF) && (type1.id() != TipoT.PUNTERO)){
            GestionErroresExp.errorSem(this.ulex,"Error en la operación unaria: " + id.name() + ". El operando no es un puntero: " + opnd1.toString());
            return false;
        }
        if((id == TipoE.DIRMEM) && (opnd1.id() != TipoE.IDEN && opnd1.id() != TipoE.INSTANCE && opnd1.id() != TipoE.SUBINDICE)){
            GestionErroresExp.errorSem(this.ulex,"Error en la operación unaria: " + id.name() + ". El operando no es referenciable : " + opnd1.toString());
            return false;
        }
        if(id == TipoE.DEREF){
            this.tipo = ((TPuntero) type1).getSub();
        }
        if(id == TipoE.DIRMEM){ 
            this.tipo = new TPuntero(type1);
        }

        return ok;
    }
}

