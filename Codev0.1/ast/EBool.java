package ast;
import alex.UnidadLexica;
import asem.PilaTablas;
import errors.GestionErroresExp;

public class EBool extends E {
    private String v;
    public EBool(String v) {
        this.v = v;
    }
    public EBool(UnidadLexica ul) {
        this.ulex = ul;   
        this.v = ul.lexema();
    }
    public String bool() {return v;}
    public TipoE id() {return TipoE.BOOL;}
    public String toString() {return v;}
    public void vincula(PilaTablas pt){
    }
    public boolean chequea(){
        this.tipo = new TBasic(TipoT.BOOL);
        return true;
    }
}

