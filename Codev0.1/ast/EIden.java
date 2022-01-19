package ast;
import alex.UnidadLexica;
import asem.PilaTablas;
import errors.GestionErroresExp;

public class EIden extends E {
  private String iden;
  public EIden(String iden) {
   this.iden = iden;   
  }
  public EIden(UnidadLexica ul) {
   this.ulex = ul;   
   this.iden = ul.lexema();
  }

  public String iden() {return iden;}
  public TipoE id() {return TipoE.IDEN;}   
  public String toString() {return iden;}  
  
    public void vincula(PilaTablas pt){
        S s = pt.buscaId(iden, this.ulex);
        this.link = s;
    }
    public boolean chequea(){ // Puede vincular con: SDecl, SFuncion, SEnumElement, STipoIden
                             //no con SClase, SUsing ni SEnum
        this.tipo = this.link.tipo;
        if(link.id() != TipoS.DECL && link.id() != TipoS.FUNCTION && link.id() != TipoS.ENUMELEMENT && link.id() != TipoS.STIPOIDEN){
            GestionErroresExp.errorSem(this.ulex,"Identificador en expresión vinculado a la construcción " + link.id() + " de la linea: " + link.getUL().fila());
            return false;
        }
        return true;
    }

}
