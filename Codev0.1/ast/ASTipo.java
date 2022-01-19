package ast;

public class ASTipo {

    public T ent() {
        return new TBasic(TipoT.INT);
    }
    public T bul() {
        return new TBasic(TipoT.BOOL);
    }
    public T chr() {
        return new TBasic(TipoT.CHAR);
    }
    public T vid() {
        return new TBasic(TipoT.VOID);
    }
    public T flout() {
        return new TBasic(TipoT.FLOAT);
    }
    public T punt(T sub) {
        return new TPuntero(sub);
    }
    public T array(T sub) {
        return new TArray(sub);
    }
    public T array(T sub, E off) {
        return new TArray(sub, off);
    }
    public T iden(EIden id){
        String i = id.iden();
        return new TIden(i);
    }

}
