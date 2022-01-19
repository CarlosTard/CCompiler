int a = 4;
int i = 3;
int[2] v;
int c = v[a];
using a = int;
int c = i--;
bool d = 4 < 5;
//a b = 2 + 3 < 5;
--i;

int a( ){ // maxMemory = 4;
    int c = 4;
    {   
        int a = 5;
        {
            int[2] v ;
        }
    }
   switch(c) { // Hemos cambiado el switch. Antes solo admitia identificadores, ahora admite todo tipo de expresiones, siempre que el resultado sea enumerable
	case(1){
        return 0 + 4 + i;
		// code block
	}case(2){
		// code block
	}case(3){
    }case(4){
    }
    }
    return 0;
}
int b(int[3] v){ // maxMemory = 7;
    int c = 4;
    {   
        int a = 5;
        {
            int[2] v;
        }
    }
   switch(c) { // Hemos cambiado el switch. Antes solo admitia identificadores, ahora admite todo tipo de expresiones, siempre que el resultado sea enumerable
	case(1){
        return 0 + 4 + i;
		// code block
	}case(2){
		// code block
	}case(3){
    }case(4){
    }
}
    return 0;
}
int c(int[3] v){ // maxMemory = 8;
    int c = 4;
    {   
        int a = 5;
        {
            int[2] v ;
        }
    }
   switch(c) { // Hemos cambiado el switch. Antes solo admitia identificadores, ahora admite todo tipo de expresiones, siempre que el resultado sea enumerable
        case(1){
            return 0 + 4 + i;
            // code block
        }case(2){
            int[4] f;
            // code block
        }case(3){
            int e = 4;
        }case(4){
            int e = 5;
        }
    }
    if(true){
        int[2] v;
    }else{
        int a = 4;
    }
    return 0;
}

int d(int[3] v){ // maxMemory = 9;
    int c = 4;
    {   
        int a = 5;
        {
            int[2] v;
        }
    }
   switch(c) { // Hemos cambiado el switch. Antes solo admitia identificadores, ahora admite todo tipo de expresiones, siempre que el resultado sea enumerable
        case(1){
            return 0 + 4 + i;
            // code block
        }case(2){
            int[4] f;
            // code block
        }case(3){
            int e = 4;
        }case(4){
            int e = 5;
        }
    }
    if(true){
        int[5] v;
    }else{
        int a = 4;
    }
    return 0;
}

int c(int[3] v, int s){ // maxMemory = 6;
    int i = 3;
   for(int i= 0; i < 4;++i){
       int v = 4;
   }
   return 0;
}
a();
int c = a();

using lol = int[34]*[2];
lol b;
lol a = b; // tiene que decir el tipo del using

int[3] q = {1,2,3}; // buen array.
//~ // int[3] q = {1,'a','b'}; // mal array
//~ // int[3] q = {'b','a','b'}; // horrible array
//~ // int[3] q = {'b','a',true}; // thing
//~ // int[3][1] q = {[1],[2],[3,2]}; //mal, pues mal size de la tercera posicion

int v = 1;
int* w = &v;
int sol = *w;
bool la = true;
char si = 'c';
//~ int** v= &w;

int* c = &sol;  
int[]** d = &(b[3]); //mal, tendria que dar error
int** f = &(c);



//~ int expresionEnumerable = 0;

//~ enum EN = {ENUMEE, ENAM, ENAeM};
//~ enum OK = {OKIO, OKI};
//~ EN expresionEnumerable = ENAM;
//~ // EN expresionIncorrecta = OKI;


//~ switch(expresionEnumerable) { // Hemos cambiado el switch. Antes solo admitia identificadores, ahora admite todo tipo de expresiones, siempre que el resultado sea enumerable
	//~ case(ENUMEE){
		//~ // code block
	//~ }case(ENUMEE){
		//~ // code block
	//~ }case(ENUMEE){
    //~ }case(ENAeM){
    //~ }
	//~ default{
		//~ // code block
	//~ }
//~ }

class ClaseConMetodos{
    int v = 5;
    
    int lechuga(){
        int a =3;
        return 9;
    }
    
    int tomate(){
        int v = 3;
        return 5;
    }
}

class NombreStruct{ //Struct, que es una clase sin metodos
	int b = 3;
    int a = 3;
    int b = 3;
    int c;
}

class ClaseRara{
    int y;
    NombreStruct hello; 
    int u;
    int lechuga(){
        return 9;
    }
}
ClaseRara o;
int c = o.hello.b;

// int coso2(int bla, char ble){ // función mal tipada
//    return ble;
//    return bla;
//    return true;
//}

// NombreStruct iamstruct;
// iamstruct.a = 7;

// ClaseConMetodos ihavefunc;
// int hola = ihavefunc.lechuga();
// char mal = ihavefunc.tomate();

// ClaseRara iamweird;
// iamweird.hello = iamstruct;
// int good = iamweird.hello.c;
// char bad = iamweird.hello.a;


 int coso4(int bla, char ble){
    return 4+5;
}

int coso6(int blo, char bli, char * blu){
    return coso6(blo,bli, blu);
}


int a = coso4(sol, si);
int c = coso6(sol, si, &si);
 int re = q[1];
 int mi = q[77];
 int fa = q[1];


void coso5(){
}

//~ class NombreStruct{ //Struct, que es una clase sin metodos
	//~ int b = 3;
    //~ int a = 3;
    //~ int b = 3;
    //~ int b = 3;
//~ }
//~ NombreStruct* a;
//~ (*a).b = 4;
//~ jaja = xd;
//~ lol = xd;

//~ int hola(){
    //~ hola();
//~ }

//~ int a( ){
    //~ int uno = 9;
    //~ int dos = 2;
    //~ int tres = 3;
    //~ int cuatro = cuatro +1;
    //~ ;

    //~ int cuatro = cuatro +1;
    //~ int cinco = cinco;
    //~ NombreStruct c = a;
    //~ c.lechuga(); // los puntos vinculan malll. En fase de checkeo de tipos se arregla espero
    //~ int seis = c.a;
    //~ hola();
//~ }

//~ hola();
//~ int externa0 = 3;
//~ {
    
        //~ hola();
        
    
    
    //~ }
//~ if (a < 3){
    //~ xd =3;
    //~ }
    
//~ x = true;

