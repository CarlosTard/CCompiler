package ast;

import alex.UnidadLexica;
import asem.PilaTablas;
import errors.GestionErroresExp;

public class ENum extends E {

    private String v;
    private boolean isReal;
    public ENum(String v, boolean isR) {
        this.v = v;   
        this.isReal = isR;
    }
    public ENum(UnidadLexica ul, boolean isR) {
        this.ulex = ul;   
        this.v = ul.lexema();
        this.isReal = isR;
    }
    public String num() {return v;}
    public TipoE id() {return TipoE.NUM;}   
    public String toString() { return v;}  
    public void vincula(PilaTablas pt){

    }
    public boolean chequea(){
        if(isReal)
            this.tipo = new TBasic(TipoT.FLOAT);
        else this.tipo = new TBasic(TipoT.INT);
        return true;
        
    }
}
