// Programa de ejemplo otro.cc

enum yes = {uno,dos,tres,catorce};// prueba de enums


//~ // prueba de función, for y asignaciones
int factFor(int n){
    int fact;
    fact = 1;
    for(int i = 2; i <= n; ++i)
        fact = fact * i;
    
    return fact;
}

// prueba de while
int factWhile(int n){
    int fact;
    fact = 1;
    int i = 2;
    while(i <= n){
        fact = fact * i;
        i++;
    }
    return fact;
}

//~ // prueba de función sin argumentos, if y switch
void trying(){
    bool ok = true;
    int n = 4;
    
    if(ok)
    print("hola");
    else print("adios");
    
    switch(n){
        case (1) 
            {print("jaja");}
        case (2)
            { print("jeje");}
        case (3) 
            {print("jiji");}
        case (4)
            { print("jojo");}
        default {print("juju");}
    }
    switch(enumerable){
        case (en1) 
            {print("jaja");}
        case (en2)
            { print("jeje");}
        case (en3) 
            {print("jiji");}
        case (en4)
            { print("jojo");}
    }
}

int main(){
    int n = 3;
    
    int a = factFor(n);
    int b = factWhile(n);
    trying();
    
    
    return 0;
}

return
