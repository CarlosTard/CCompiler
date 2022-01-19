package ast;
import java.util.ArrayList;
import java.util.Collections;
import alex.UnidadLexica;
public class ASExp {

    public E suma(E opnd1, E opnd2) {
        return new EBin(opnd1,opnd2,TipoE.SUMA);
    }
    public E mul(E opnd1, E opnd2) {
        return new EBin(opnd1,opnd2,TipoE.MUL);
    }
    public E resta(E opnd1, E opnd2) {
        return new EBin(opnd1,opnd2,TipoE.RESTA);
    }
    public E div(E opnd1, E opnd2) {
        return new EBin(opnd1,opnd2,TipoE.DIV);
    }
    public E and(E opnd1, E opnd2) {
        return new EBin(opnd1,opnd2,TipoE.AND);
    }
    public E or(E opnd1, E opnd2) {
        return new EBin(opnd1,opnd2,TipoE.OR);
    }
    public E igual(E opnd1, E opnd2) {
        return new EBin(opnd1,opnd2,TipoE.IGUALLOG);
    }
    public E notigual(E opnd1, E opnd2) {
        return new EBin(opnd1,opnd2,TipoE.NOTIGUAL);
    }
    public E menor(E opnd1, E opnd2) {
        return new EBin(opnd1,opnd2,TipoE.MENOR);
    }
    public E menorigual(E opnd1, E opnd2) {
        return new EBin(opnd1,opnd2,TipoE.MENORIGUAL);
    }
    public E mayor(E opnd1, E opnd2) {
        return new EBin(opnd1,opnd2,TipoE.MAYOR);
    }
    public E mayorigual(E opnd1, E opnd2) {
        return new EBin(opnd1,opnd2,TipoE.MAYORIGUAL);
    }
    public E mod(E opnd1, E opnd2) {
        return new EBin(opnd1,opnd2,TipoE.MOD);
    }
    public E pow(E opnd1, E opnd2) {
        return new EBin(opnd1,opnd2,TipoE.POW);
    }
    public E subindice(E opnd1, E opnd2) {
        return new EBin(opnd1,opnd2,TipoE.SUBINDICE);
    }
    public E instance(E opnd1, E opnd2) {
        return new EBin(opnd1,opnd2,TipoE.INSTANCE);
    }
    public E elist(){
        return new EList();
    }
    
    // @SuppressWarnings("unchecked")
    // El casting produce warnings, pero sabemos que es seguro.
    public E callFunction(E opnd1, E opnd2) {
        // opnd2 = (ArrayList<E>) opnd2;
        return new EBin(opnd1,opnd2,TipoE.FUNCTION);
    }
    public E opuesto(E opnd1) {
        return new EUn(opnd1,TipoE.OPUESTO);
    }
    public E not(E opnd1) {
        return new EUn(opnd1,TipoE.NOT);
    }
    public E masmas(E opnd1) {
        return new EUn(opnd1,TipoE.MASMAS);
    }
    public E menosmenos(E opnd1) {
        return new EUn(opnd1,TipoE.MENOSMENOS);
    }
    public E deref(E opnd1) {
        return new EUn(opnd1,TipoE.DEREF);
    }
    public E dirmem(E opnd1) {
        return new EUn(opnd1,TipoE.DIRMEM);
    }

    public E num(UnidadLexica num) {
        return new ENum(num, false);
    }
    public E numR(UnidadLexica num) {
        return new ENum(num, true);
    }
    public E bool(String bool) {
        return new EBool(bool);
    }
    public E car(String car) {
        return new ECar(car.charAt(1)); 
    }
    public E bool(UnidadLexica bool) {
        return new EBool(bool);
    }
    public E car(UnidadLexica car) {
        return new ECar(car); 
    }
    public E iden(String i) { 
        return new EIden(i);
    }
    public E iden(UnidadLexica ul) { 
        return new EIden(ul);
    }
    public E enew(T type){
        return new ENew(type);
    }
        
    
}
