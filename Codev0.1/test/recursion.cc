void recursiva(int a){
    print(a);
    if(a <= 4)
        recursiva(a + 1);
    print(a);
}
void printea(int b){
    print(b);
}
int dameUnDos(int a){
    return 2;
}


void main(){
    recursiva(1);
    print(111111111); // para separar
    print(dameUnDos(dameUnDos(3)));
    print(111111111); // para separar
    recursiva(dameUnDos(222222));
}
