// Producto de matrices. Contenidos: bucles anidados, creación y acceso a arrays y punteros
int[3][3]* mulMat(int[3][3] a, int[3][3] b){ 
    int[3][3] c;
    for(int i = 0;i < 3; ++i){ //version con for. i es variable local bloque funcion
        for(int j = 0;j < 3;++j){//j variable local bloque anidado 1
            c[i][j] = 0;
            for(int k = 0;k<3;++k) {// k variable local bloque anidado 2
                c[i][j] = c[i][j] + a[i][k] * b[k][j];
            }
        }
    }
    int[3][3]* punt = &c;
    return punt;
}
// Producto de matrices. Contenidos: clase con atributos y metodo, acceso a atributos desde el metodo,bucles anidados, y acceso a puntero desde funcion
class MatMult{
	int[3][3] a;
	int[3][3] b; //atributos.
	
	void mulMat(int[3][3]* c){ 
		for(int i = 0;i < 3; ++i){ 
			for(int j = 0;j < 3;++j){
                (*c)[i][j] = 0;
				for(int k = 0;k<3;++k) {
					(*c)[i][j] = (*c)[i][j] + a[i][k] * b[k][j]; // accedemos a atributos desde el metodo
				}
			}
		}
	}
}


void main(){
    int[3][3] a;
    int[3][3] b;
    for(int i = 0; i < 3; ++i){
        for(int j = 0; j < 3; ++j){
            a[i][j] = 3*i+j+1;
            b[i][j] = 3*(3-i)-j;
        }
    }
    for(int i = 0; i < 3; ++i){
        for(int j = 0; j < 3; ++j){
            print(a[i][j]); 
        }
        print(-1);//para separar
    }
    for(int i = 0; i < 3; ++i){
        for(int j = 0; j < 3; ++j){
            print(b[i][j]);
        }
        print(-1);//para separar
    }
    int[3][3]* prod = mulMat(a,b);
    // prod = {{1,2,3},{4,5,6},{7,8,9}}*{{9,8,7},{6,5,4},{3,2,1}} = {{30,24,18},{84,69,54},{138,114,90}}
    for(int i = 0; i < 3; ++i){
        for(int j = 0; j < 3; ++j){
            print((*prod)[i][j]);
        }
        print(-1);//para separar
    }
    print(111111); // para separar
    int[3][3] result;
    MatMult m; //Declaracion y creacion instancia de clase MatMult
    for(int i = 0; i < 3; ++i){
        for(int j = 0; j < 3; ++j){
            m.a[i][j] = 3*i+j+1; //establecemos el valor inicial de los atributos del objeto
            m.b[i][j] = 3*(3-i)-j;
        }
    }
    //veamos los valores que hemos dado a los atributos del objeto
    for(int i = 0; i < 3; ++i){
        for(int j = 0; j < 3; ++j){
            print(m.a[i][j]); 
        }
        print(-1);//para separar
    }
    for(int i = 0; i < 3; ++i){
        for(int j = 0; j < 3; ++j){
            print(m.b[i][j]);
        }
        print(-1);//para separar
    }
    m.mulMat(&result);  //acceso a metodo mulMat del objeto m, pasando referencia de result
    
    
    for(int i = 0; i < 3; ++i){
        for(int j = 0; j < 3; ++j){
            print(result[i][j]);
        }
        print(-1);//para separar
    }
    
}
