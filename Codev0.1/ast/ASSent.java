package ast;
import java.util.ArrayList;
import java.util.Collections;


public class ASSent {

    public SList list(){
        return new SList();
    }
    public S getIf(E cond, S then){
        return new SIf(cond,then);
    }
    public S getIf(E cond, S then, S elsee){
        return new SIf(cond,then,elsee);
    }
    public S ret(E e0){
        return new SReturn(e0);
    }
    public S ret(){
        return new SReturn();
    }
    public S brek(){
        return new SToken(TipoS.BREAK);
    }
    public S contin(){
        return new SToken(TipoS.CONTINUE);
    }
    public S print(String s){
        return new SPrint(s);
    }
    public S print(E e){
        return new SPrint(e);
    }
    public S prunt(EIden i){
        return new SPrunt(i);
    }

    public S using(E iden, T type){
        return new SUsing(iden,type);
    }
    @SuppressWarnings("unchecked")
    public S senum(E iden, E members){
        return new SEnum(iden,members);
    }
    
    @SuppressWarnings("unchecked")
    public S sclase(E iden, ArrayList attrList, ArrayList functionList){
        attrList = (ArrayList<S>) attrList;
        functionList = (ArrayList<S>) functionList;
        return new SClase(iden,attrList,functionList);
    }
    
    @SuppressWarnings("unchecked")
    public S sclase(E iden, ArrayList attrList){
        attrList = (ArrayList<S>) attrList;
        return new SClase(iden,attrList);
    }
    
    public S sfor(S dec, E exp, S condAv, S instructs){
        return new SLoop(dec,exp,condAv,instructs);
    }
    
    public S swhile(E exp, S instructs){
        return new SLoop(exp,instructs);
    }

    public S sswitch(E var, ArrayList<S> cases){
        return new SSwitch(var,cases);
    }

    public S sswitch(E var, ArrayList<S> cases, S def){
        return new SSwitch(var,cases,def);
    }

    public S scase(E var, S block){
        return new SCase(var,block);
    }
    
    @SuppressWarnings("unchecked")
    public S function(S tipo, ArrayList args, S block){
        args = (ArrayList<S>) args;
        return new SFunction(tipo,args,block);
    }
    
    public S se(E e){
        return new SE(e);
    }
    public S asig(E ladoIz, E ladoDer){
        return new SAsig(ladoIz,ladoDer);
    }
    public S decl(STipoIden tipoIden, E ladoDer){
        return new SDecl(tipoIden,ladoDer);
    }

    public STipoIden stipoiden(T t, EIden iden){
        return new STipoIden(t,iden);
    }
    
    public SReduce sr(String iden){
        return new SReduce(iden);
    }
    public SReduce sr(SReduce sr, E off){
        return new SReduce(sr, off);
    }
    public S serror(){
        return new SError();
    }

}
