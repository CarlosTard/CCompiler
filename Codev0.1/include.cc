// Fichero de ejemplo includer.cc. Contenido: Operaciones bis implementadas (resta y división), switch, bloques anidades (for y switch), continue y break



void main(){
    int a = 18;
    int b = 180;
    for(int i = 0; i < 6; ++i){
        switch(i){
            case(0){
                print(a-b); // resta normal
            }
            case(1){
                print(a-:b); // resta "bis" (o resta con asociativadad invertida)
            }
            case(2){
                print(a/b); // división normal
            }
            case(4){
                print(a/:b); // división "bis" (o división con asociatividad invertida)
            }
            case(3){
                continue;
            }
            default{
                break;
            }
        }
        print(i);
    }
}

