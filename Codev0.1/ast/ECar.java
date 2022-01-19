package ast;
import alex.UnidadLexica;
import asem.PilaTablas;
import errors.GestionErroresExp;

public class ECar extends E {
    private char c;
    public ECar(char c) {
        this.c = c;   
    }
    public ECar(UnidadLexica ul) {
        this.ulex = ul;   
        this.c = ul.lexema().charAt(1); ///hay que ver si da index out of bounds exception;
    }

    public char car() {return c;}
    public TipoE id() {return TipoE.CHAR;}   
    public String toString() {return "'" + Character.toString(c) + "'";}  
    public void vincula(PilaTablas pt){
    }
    public boolean chequea(){
        this.tipo = new TBasic(TipoT.CHAR);
        return true;
    }
}
