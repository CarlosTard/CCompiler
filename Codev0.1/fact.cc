// Programa de ejemplo fact.cc
 #include "jaja.cc";

enum yes = {uno,dos,tres,catorce};// prueba de enums

class hola{
    int a;
    bool b;
    
    int hola2(){
        print("hello");
        return 2;
    }
    
    int adios(){
        print("bye");
        return 7;
    }
}

class nofuncs{
    int a;
    bool b;
}

int factFor(int n){
	int fact; // Inicializamos fact
	fact = 1; // le asignamos el valor 1
	for(int i = 2; i <= n; ++i)
		fact = fact * i;
	
	// La sintaxis de for es como la de C++ con for(variable que se va a modificar, condiciones, modificacion de la variable)
	return fact; // Devolvemos fact con la instruccion return
}

int factRecursivo(int n){
	if(n == 1)
		return 1;
	else{
		return n*factRecursivo(n-1);
	}
}

void trying(){
    bool ok = true;
    int n = 4;
    
    if(ok)
    print("hola");
    else print("adios");
}

int main(){
	int n = 3;

	int a = factFor(n);
	int b = factWhile(n);
	int c = factRecursivo(n);
    trying();
	// a = b = c = 6


	return 0;
}
