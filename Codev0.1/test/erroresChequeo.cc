

class Struct{
    char a;
    bool b;
    int c;
}

class Clase{
    int d;
    bool e;
    char f;
    
    void fun1(int a, int b){
        print(a);
    }
    
    int fun2(){
        return 3;
    }
    
    int fun3(int v, int w){
        return v+w;
    }
    
    void fun4(){
        return 9; // fila: 27 col: 9: Error semántico: Error en la función fun4. El tipo del return y la función no son el mismo: VOID, INT
    }
    
    int fun5(){
        int a = 7;
        if(a < 5){
            return 9;
        }
        return 'l'; // fila: 35 col: 9: Error semántico: Error en la función fun5. El tipo del return y la función no son el mismo: INT, CHAR
    }
}


void main(){
    int a = 7;
    int b = 9;
    int *punt = a+b; // fila: 43 col: 15: Error semántico: Los tipos a ambos lados de la igualdad no coinciden: (INT)* es distinto de INT
    Struct cl1;
    Clase cl2;
    bool c = 'l'; // fila: 46 col: 12: Error semántico: Los tipos a ambos lados de la igualdad no coinciden: BOOL es distinto de CHAR
    bool d = true;
    cl1.a = false; // fila: 48 col: 11: Error semántico: Los tipos no coinciden: CHAR, BOOL
    
    cl2.fun1(); // fila: 50 col: 13: Error semántico: Numero de argumentos incorrecto
    int e = cl1.fun1(a,b); // fila: 51 col: 16: Error semántico: Error en la operación punto. No se ha encontrado definicion de atributo: fun1 en la clase: Struct
}



