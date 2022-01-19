package ast;
import java.util.ArrayList;
import asem.PilaTablas;
import asem.PilaTablas;
import errors.GestionErroresExp;

public class SDecl extends S{
    private EIden iden;
    private E ladoDer;
    
    public SDecl(STipoIden tipoIden, E ladoDer){
        super(TipoS.DECL);
        this.tipo = tipoIden.type();
        this.iden = tipoIden.iden();
        this.ladoDer = ladoDer;
    }
    
    public EIden iden(){
        return iden;
    }
    public E ladoDer(){
        return ladoDer;
    }

    public String toString(int h){
        StringBuilder sb = new StringBuilder();
        String tabs = getTabs(h);
        if(ladoDer == null)
            sb.append(tabs +"|" + this.tipo + " " + this.iden + ";");
        else
            sb.append(tabs +"|" + this.tipo + " " + this.iden + " = " + ladoDer + ";");

        return sb.toString();
    }
    public void vincula(PilaTablas pt){
        this.tipo = tipo.vinculaT(pt);
        if(ladoDer != null)
            ladoDer.vincula(pt); // En este orden para encontrar errores del tipo: int a = a+1;
        pt.insertaId(iden.iden(), this, this.ulex);
        iden.link = this;
    }
    
    public boolean chequea(){
        boolean ok = this.tipo.chequea();
        if(this.tipo.id() == TipoT.VOID){
             GestionErroresExp.errorSem(this.ulex,"Chequeo de tipos fallido. Declaración tipo VOID");
                ok = false;
         }
        if(ladoDer != null){
            if(!ladoDer.chequea()){
                ok =false;
            }else if(!ladoDer.tipo.mismoTipo(this.tipo)){ 
                GestionErroresExp.errorSem(this.ulex,"Los tipos a ambos lados de la igualdad no coinciden: " + tipo + " es distinto de " + ladoDer.tipo);
                return false;
            }
        }
        return ok;
    }
}
