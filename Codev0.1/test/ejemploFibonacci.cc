// Ejemplo de Fibonacci. Contenidos: acceso a arrays y punteros, pasar punteros como parámetos, acceso a objetos

class Fibonacci{
    int irrelevant;
    
    
    int nesimo(int n){ // devuelve el n-ésimo número de Fibonacci
        if((n == 0) || (n == 1))
            return 1;
        int sol = nesimo(n-1) + nesimo(n-2);
        return sol;
    }
    
    void nprimeros(int n, int[200]* arrSol){ // Función que devuelve los n primeros números de Fibonacci
        int[200] arr;
        for(int k = 0; k < n; ++k){
            if((k == 0) || (k == 1)){
                (*arrSol)[k] = 1;
                arr[k] = 1;
            }
            else{
                int num = arr[k-1] + arr[k-2];
                (*arrSol)[k] = num;
                arr[k] = num;
            }
        }
    }
    
}


void main(){
    Fibonacci fib;
    int n1 = 20;
    int n2 = 10;
    int n3 = 30;
    int[200] arrFib;
    
    int sol = fib.nesimo(n1);
    print(sol);
    print(-1); // Usamos -1 como salto de línea improvisado
    fib.nprimeros(n1,&arrFib);
    for(int i = 0; i < n1; ++i)
        print(arrFib[i]);
    print(-1);
    sol = fib.nesimo(n2);
    print(sol);
    print(-1);
    fib.nprimeros(n2,&arrFib);
    for(int j = 0; j < n2; ++j)
        print(arrFib[j]);
    print(-1);
    sol = fib.nesimo(n3);
    print(sol);
    print(-1);
    fib.nprimeros(n3,&arrFib);
    for(int k = 0; k < n3; ++k)
        print(arrFib[k]);
}

