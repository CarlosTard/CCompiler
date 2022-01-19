void main(){
    int[3] a_; //Error lexico fila 2 columna 13, caracter inesperado _
    int[3] b =};
    int[3] c;

    int i_ = 0; //Error lexico fila 6 columna 10. Tiene que detectar ambos
    while(i < 3){
        c[i] = a[i]*b[i];
        i++;
    }
}
