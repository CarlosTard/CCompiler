// Fichero de ejemplo includer.cc. Contenido: Include, using, operaciones bis implementadas (resta y división), switch, bloques anidades (for y switch), continue y break
#include "test/pruebaInclude.cc";

using ent = int;



void main(){
    ent n = 49;
    print(n);
    int a = 18;
    int b = 180;
    tokFunction();
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
    includedVoidFunction();
    int sol = includedIntFunction(a,b);
    // Funciones de pruebaInclude.cc
    print(sol);
}
