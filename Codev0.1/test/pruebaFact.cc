// Programa de ejemplo pruebaFactorial.cc. Contenidos: Funciones con parámetros y que devuelven un resultado, bucles y recursión

int factFor(int n){
    int fact; // Inicializamos fact
    fact = 1; // le asignamos el valor 1
    for(int i = 2; i <= n; ++i)
        fact = fact * i;
    
    // La sintaxis de for es como la de C++ con for(variable que se va a modificar, condiciones, modificacion de la variable)
    return fact; // Devolvemos fact con la instruccion return
}

int factWhile(int n){ // puntero como parámetro
    int fact; // Inicializamos fact
    fact = 1; // le asignamos el valor 1
    int i = 2; // creamos la variable
    while(i <= n){
        fact = fact * i;
        i++;
    }
    // La sintaxis de while es como la de C++ con while(condiciones)
    return fact; // Devolvemos fact con la instruccion return
}

int factRecursivo(int n){
    if(n == 1)
        return 1;
    else{
        return n*factRecursivo(n-1);
    }
}

void main(){
    int n = 3;
    
    int a = factFor(n);
    int b = factWhile(n);
    int c = factRecursivo(n);
    int d = factRecursivo(factRecursivo(n));
    print(a);
    print(b);
    print(c);
    print(d);
    // a = b = c = 6
    // d = 720
}
