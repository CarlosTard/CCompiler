void main(){
    int a = 7;
    int b = 9;
    int b = a; // fila: 4 col: 11: Warning semántico: hay varios identificadores llamados: b. En este caso tomamos el último
    int c = d; // fila: 5 col: 13: Error semántico: No encontrado identificador: d
    ll e = 17; // fila: 6 col: 5: Error semántico: No encontrado identificador: ll
}


