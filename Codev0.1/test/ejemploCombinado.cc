class NombreStruct{ //Struct, que es una clase sin metodos
	int b;
    int a;
    int c;
    int lechuga(){
        a = 16;
        print(1);
        return 1;
    }
}

class ClaseRara{
    int y;
    NombreStruct hello; 
    int u;
    int lechuga(){
        y = 99;
        print(9);
        return 9;
    }
    void tomate(ClaseRara cr2){
        cr2.u = 1;
        u = 66;
    }
}
int lechuga(){
    print(0);
    return 0;
}


int funcion( int[2] v, int[2][2] mat,ClaseRara cr){
    for(int i = 0;i < 2;++i)
        print(v[i]);
    for(int i = 0;i < 2;++i){
        for(int j = 0;j < 2;++j){
          print(mat[i][j]);
        }
    }
    print(cr.hello.a);
    print(cr.hello.b);
    print(cr.hello.c);
    print(cr.y);
    return 0;
}

void main(int c){
    int[2][2] mat;
    for(int i = 0;i < 2;++i){
        for(int j = 0;j < 2;++j){
          mat[i][j] = 2*i + j + 10;
        }
    }
    NombreStruct he;
    he.a = 4444;
    he.b = 6666;
    he.c = 2000;
    ClaseRara cr;
    ClaseRara cr2;
    cr.hello.a = 4545;
    cr.hello.b = 3434;
    cr.hello.c = 1212;
    cr.y = 454545;
    cr2.y = 0; cr2.u = 0;
    cr2.hello.a = 0;
    funcion(mat[0],mat,cr); // podemos pasar columnas de matrices como si fueran vectores
    print(1111111111);
    print(lechuga());
    print(cr.lechuga()); //cambia el atributo cr.y 
    print(cr.hello.lechuga());//cambia el atributo cr.hello.a 
    print(111111);
    print(cr.y); //ha cambiado
    print(cr.hello.a); //ha cambiado
    print(cr2.y); //no ha cambiado, es objeto distinto
    print(cr2.hello.a); //no ha cambiado, es objeto distinto
    cr.tomate(cr2); // cambia el mismo atributo de cr y de cr2, dandole valores distintos
    print(cr.u); //debería salir 66
    print(cr2.u); //deberia salir 0, pues hemos pasado cr2 por valor

}

