package ast;
import java.util.ArrayList;
import asem.PilaTablas;
import errors.GestionErroresExp;

public class SFunction extends S{
    private EIden iden;
    private ArrayList<S> args;
    private S block;

    public String funcName;
    
    public SFunction(S tipoIden, ArrayList<S> args, S block){
        super(TipoS.FUNCTION);
        this.tipo = ((STipoIden) tipoIden).tipo;
        this.iden = ((STipoIden) tipoIden).iden();
        this.args = args;
        this.block = block;
        this.funcName = this.iden.iden();
    }

    
    public S getBlock(){
        return block;
    }
    public ArrayList<S> getArgs(){
        return args;
    }
    public EIden iden(){
        return iden;
    }
    
    public String toString(int h){
        StringBuilder sb = new StringBuilder();
        String tabs = getTabs(h);
        sb.append(tabs +"|" + this.tipo + " " + this.iden + "(");
        for(int i = 0; i < args.size(); i++){
            sb.append(args.get(i).toString());
            if(i < args.size()-1)
                sb.append(",");
        }
        sb.append("){\n");
        sb.append(block.toString(h+1) + "\n");
        sb.append(tabs + " }");
        return sb.toString();
    }
    
    public void vincula(PilaTablas pt){
        this.tipo = tipo.vinculaT(pt);
        pt.insertaId(iden.iden(), this, this.ulex);
        pt.abreBloque();
        for(S arg : args){
            arg.vincula(pt);
        }
        block.vincula(pt);
        pt.cierraBloque();
    }
    
    /*
     Esta función devuelve -1 si encuentra algún return que no concuerda con el tipo de la función. Si no hay error, devuelve 0 si no hay returns y 1 en caso contrario. Creo que se podría hacer con bool.
     */
    
    public int checkReturns(S instrs, T tipoFunc){
        int retNum = 0;
        int aux = 0;
        if(instrs.id() == TipoS.RETURN){
            T other = ((SReturn) instrs).tipo;
            if(!tipoFunc.mismoTipo(((SReturn) instrs).tipo)){
                GestionErroresExp.errorSem(instrs.getUL(),"Error en la función " + iden.toString() + ". El tipo del return y la función no son el mismo: " + tipoFunc + ", " + ((SReturn) instrs).tipo);
                retNum = -1;
            }
            else retNum = 1;
        }
        else if(instrs.id() == TipoS.FUNCTION){
            GestionErroresExp.errorSem(instrs.getUL(),"Función " + ((SFunction) instrs).iden().toString() + " definida dentro de función " + iden.toString());
            retNum = -1;
        }
        else if (instrs.id() == TipoS.LIST){
            SList list = (SList) instrs;
            ArrayList<S> block = list.get();
            for(S s: block){
                aux = checkReturns(s,tipoFunc);
                if(aux == -1||(aux == 1 && retNum != -1))
                    retNum = aux;
            }
        }
        else if (instrs.id() == TipoS.FOR||instrs.id() == TipoS.WHILE){
            S insts = ((SLoop) instrs).getInstructs();
            if (insts != null){
                aux = checkReturns(insts,tipoFunc);
                if(aux == -1||(aux == 1 && retNum != -1))
                    retNum = aux;
            }
        }
        else if (instrs.id() == TipoS.IF){
            S then = ((SIf) instrs).getThen();
            S elsee = ((SIf) instrs).getElse();
            retNum = 0;
            int retNumElse = 0;
            aux = 0;
            if(then != null){
                retNum = checkReturns(then,tipoFunc);
            }
            if(elsee != null){
                retNumElse = checkReturns(elsee,tipoFunc);
                if(retNum == 1 && retNumElse == 1)
                    return 1;
                if(retNum == 0 || retNumElse == 0)
                    return 0;
                return -1;
            }else{
                if(retNum == -1) return -1;
                else return 0;
            }
            
        }
        else if (instrs.id() == TipoS.SWITCH){
            ArrayList<S> cases = ((SSwitch) instrs).getCases();
            S def = ((SSwitch) instrs).getDef();
            for(S s: cases){
                aux = checkReturns(((SCase) s).getBlock(),tipoFunc);
                if(aux == -1||(aux == 1 && retNum != -1))
                    retNum = aux;
            }
            
            if(def != null){
                aux = checkReturns(def,tipoFunc);
                if(aux == -1||(aux == 1 && retNum != -1))
                    retNum = aux;
                if(aux == 0)
                    return 0;
            }else{
                if(retNum == -1) return -1;
                else return 0;
            }
        }
        
        return retNum;
    }
    
    public boolean chequea(){ 
        boolean ok = true;
        if(!this.tipo.chequea())
            return false;
        this.tipo = new TFuncion(this.tipo, args, this);
        for(S s: args){
            if(!s.chequea()){
                ok = false;
            }
        }
        if(!block.chequea()){
            ok = false;
        }
        
        int check = checkReturns(this.block,((TFuncion) this.tipo).getRet());
        
        if(check == -1){
            ok = false;
        }
        else if(check == 0 && ((TFuncion) this.tipo).getRet().id() != TipoT.VOID){
            GestionErroresExp.errorSem(this.ulex,"Función no void no tiene returns en alguna de sus ramas (mira los if/else) (y el switch/case por si falta el default)");
            ok = false;
        }
        
        return ok;
    }


   
}
