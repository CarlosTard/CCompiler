package ast;
import java.util.ArrayList;
import java.util.Collections;
import asem.PilaTablas;
import errors.GestionErroresExp;

public class SEnum extends S{
    private EIden iden;
    private E members;
    private ArrayList<SEnumElement> elements;
    
    public SEnum(E iden, E members){
        super(TipoS.ENUM);
        this.iden = (EIden) iden;
        this.members = members;
        this.elements = new ArrayList<SEnumElement>();
    }
    
    public String toString(int h){
        StringBuilder sb = new StringBuilder();
        String tabs = getTabs(h);
        sb.append(tabs +"|enum " + iden + " = " + members.toString() + ";\n");
        
        return sb.toString();
    }
    
    public EIden getIden(){
        return iden;
    }
    
    public E getMembers(){
        return members;
    }
    
    public void vincula(PilaTablas pt){
        pt.insertaId(iden.iden(), this, this.ulex);
        int i = 0;
        this.tipo = new TEnum(this);
        for(E e: ((EList) members).get()){
            EIden ee = (EIden) e;
            SEnumElement see = new SEnumElement(ee,i,this);
            this.elements.add(see);
            see.vincula(pt);
            ++i;
        }
    }
    
    public boolean chequea(){
        for(SEnumElement see: this.elements)
            see.chequea();
        return true;
    }
}

