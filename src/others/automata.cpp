#include <iostream>
#include <string>
#include <vector>
#include <ctime>
#include <fstream>
#include <windows.h>

// Crenando la clase para el Automata Finito Determinista
// Este automata ya no cuenta con la verificacion para Automatas Finitos No Deterministas
// Si se implementa, se tendria que hacer el algoritmo para conversion de AFND a AFD (complejo)
class AutomataFD {
private:
	// Sirve para guardar un estado o nodo (actual y siguiente), su nombre (actual y siguiente) y transicion (que apunta)
	// Los estados pueden ser: 
	// 0: Estado inicial 
	// 1: Estado terminal 
	// 2: Ninguno 
	// 3: Ambos (Estdo inicial y terminal, a la vez), es un caso especial

	// Esta como private, por que solo sera instanciado por la clase AutomataFD
	// Se puede haber hecho una clase en lugar de struct, pero YO, uso punteros para instanciar clases, asi que No :v
	// Nota: Si lo hubiese hecho con class, por ahi hubiese un triple puntero, asi que tambien por eso no lo use XD
	struct Estado {
		// Constructor por defecto. Sí, los struct pueden tener constructores y destructores
		// en C++, class y struct no tienen diferencias, salvo por el nombre

		Estado() {
			//this->transicion.push_back('?');
			this->estadoActual = '2';
			this->estadoApuntado = '2';
			//this->nombreActual = " ";
			//this->nombreApuntado = " ";
		}

		Estado(std::vector<char> transicion, char estadoActual, char estadoApuntado, std::string nombreActual, std::string nombreApuntado) {
			this->transicion = transicion;
			this->estadoActual = estadoActual;
			this->estadoApuntado = estadoApuntado;
			this->nombreActual = nombreActual;
			this->nombreApuntado = nombreApuntado;
		}
		
			std::vector<char> transicion;
			char estadoActual;
			char estadoApuntado;
			std::string nombreActual;
			std::string nombreApuntado;
	};

	int numeroEstados{};
	std::string nombreEstadoInicial{};
	Estado** matrizAdyacencia{};
	
	// Busca dentro de un vector si existe el caracter que se le esta indicando
	bool buscarCoincidencia(std::vector <char> auxTrans, char aux, bool& isCorrecto) {
		for (int i = 0; i < (int)auxTrans.size(); i++) {
			if (aux == auxTrans[i]) {
				isCorrecto = true;
				return true;
			}
			if (auxTrans[i] == '\0') {
				isCorrecto = true;
				return true;
			}
			else isCorrecto = false;
		}
		return false;
	}

	// Busca si dentro del arreglo existe el caracter que se le indica como parametro
	bool buscar(char caracter, char *arreglo, int size) {
		for (int i = 0; i < size; i++)
			if (caracter == arreglo[i])
				return true;

		return false;
	}
	// Esos metodos anteriores son privados pues no deben ser accesibles por el objeto que se instancie
public:

	// Define el numero de estados
	AutomataFD(int numeroEstados) {
		this->numeroEstados = numeroEstados;

		// Crea arreglo dinamico de 2 dimensiones
		matrizAdyacencia = new Estado * [numeroEstados];
		for (int i = 0; i < numeroEstados; i++)
			matrizAdyacencia[i] = new Estado[numeroEstados];
		
		// Llena valores con estados vacios
		for (int i = 0; i < numeroEstados; i++)
			for (int j = 0; j < numeroEstados; j++)
				matrizAdyacencia[i][j] = Estado();
	}
	
	// Se le indica quien sera el estado inincial y se procede a marcar a todo los (i == estadoInical, j) como estados iniciales
	// Tambien se guarda el nombre pues servira para hacer la validacion de cadenas
	void estadoInicial(std::string nombreActual) {
		nombreEstadoInicial = nombreActual;
		
		// Al nombreActual, que es quien contiene el nombre del estdo inicial se le quita el primer caracter (por ejemplo: q1, s1, t0)
		// Se le quita el primer caracter, pues lo siguientes numeros son los indices del lugar donde se ubica (i) en la matriz.
		// Por ejemplo:
		// --------------------------------------
		//  i/j	|	q0	|	q1	|	q2	|	q3	|
		// --------------------------------------
		// 	 q0	|		|		|		|		|
		// --------------------------------------
		//	 q1	|		|		|		|		|
		// --------------------------------------
		//	 q2	|		|		|		|		|
		// --------------------------------------
		// 	 q3	|		|		|		|		|
		// --------------------------------------
		// NombreActual -> i
		// NombreApuntado -> j
		// Si se le quita q, solo te quedan las posiciones. En este caso, solo nos interesa la de la i

		int indice = (int)atoi(nombreActual.substr(1, nombreActual.length() - 1).c_str());
		for (int i = 0; i < numeroEstados; i++) {
			for (int j = 0; j < numeroEstados; j++) {
				if (i == indice) {
					if (matrizAdyacencia[i][j].estadoActual == '1') {
						char aux = matrizAdyacencia[i][j].estadoApuntado;
						std::vector<char> auxT = matrizAdyacencia[i][j].transicion;
						matrizAdyacencia[i][j] = Estado(auxT, '3', aux, nombreActual, " ");
					}
					else {
						char aux = matrizAdyacencia[i][j].estadoApuntado;
						std::vector<char> auxT = matrizAdyacencia[i][j].transicion;
						matrizAdyacencia[i][j] = Estado(auxT, '0', aux, nombreActual, " ");
					}
				}
			}
		}
	}
	// Es similar (casi igual) al de estadoInicial, solo que este no guarda el nombre del estado final
	void estadoFinal(std::string nombreActual) {
		int indice = (int)atoi(nombreActual.substr(1, nombreActual.length() - 1).c_str());
		
		for (int i = 0; i < numeroEstados; i++) {
			for (int j = 0; j < numeroEstados; j++) {
				if (i == indice) {
					if (matrizAdyacencia[i][j].estadoActual == '0') {
						char aux = matrizAdyacencia[i][j].estadoApuntado;
						std::vector<char> auxT = matrizAdyacencia[i][j].transicion;
						matrizAdyacencia[i][j] = Estado(auxT, '3', aux, nombreActual, " ");
					}
					else {
						char aux = matrizAdyacencia[i][j].estadoApuntado;
						std::vector<char> auxT = matrizAdyacencia[i][j].transicion;
						matrizAdyacencia[i][j] = Estado(auxT, '1', aux, nombreActual, " ");
					}
				}
			}
		}
	}
	
	
	// Debido que en los nombres de los estado tambien se encuentra la posicion, solo se quita el primer caracter
	// Luego, se procede a insertar la nueva transicion con esa posicion (i, j)
	void agregarTransicion(std::string nombreActual, char valorTransicion, std::string nombreApuntado) {
		int indiceActual = (int)atoi(nombreActual.substr(1, nombreActual.length() - 1).c_str());
		int indiceApuntado = (int)atoi(nombreApuntado.substr(1, nombreApuntado.length() - 1).c_str());
		
		char auxEstadoActual = matrizAdyacencia[indiceActual][indiceApuntado].estadoActual;
		char auxEstadoApuntado = matrizAdyacencia[indiceActual][indiceApuntado].estadoApuntado;

		// Se usa vector, pues en una misma coordena (i, j), pueden haber muchas transiciones. También, por que vector permite
		// insertar datos sin necesidad de indicar la posicion (push_back()).
		// Lo que se hace es obtener todas las transiciones que pueden haber en esa posicion y se guarda en un vector auxiliar
		// para despues insertar la nueva transicion (en el vector auxiliar) y luego regresarlo

		std::vector<char> auxTransiciones = matrizAdyacencia[indiceActual][indiceApuntado].transicion;
		auxTransiciones.push_back(valorTransicion);
		matrizAdyacencia[indiceActual][indiceApuntado] = Estado(auxTransiciones, auxEstadoActual, auxEstadoApuntado, nombreActual, nombreApuntado);
	}

	// Este metodo se basa en saltos...
	// Por ejemplo se tiene el siguiente automata:
	// 		-------------------------
	// 		  i/j	|	0	|	1	|
	// 		-------------------------
	// 	->	   q0	|	q2	|	q0	|
	// 		-------------------------
	//	*	   q1	|	q1	|	q1	|
	// 		-------------------------
	//		   q2	|	q2	|	q1	|
	// 		-------------------------
	//  -> 	: Estado inicial
	//  *	: Estado terminal
	//		: Ninguno

	// Dicho automata se puede representar como grafo dirigido, entondes
	// se procede a construir su matriz de adyacencia

	// ------------------------------
	//  i/j	|	q0	|	q1	|	q2	|
	// ------------------------------
	// 	 q0	|	1	|	-	|	0	|
	// ------------------------------
	//	 q1	|	-	|  0,1	|	-	|
	// ------------------------------
	//	 q2	|	-	|	1	|	0	|
	// ------------------------------
	// Entonces, ahora tanto i como j representa el nombre de un nodo
	// Tambien hay que tener en cuenta que esta matriz de adyacencia no solo guarda la transicion, en la posicion i,j.
	// Sino que guarda un struct Estado(), el cuanto contiene lo siguiente:
	// 1) transiciones
	// 2) estadoActual 
	// 3) estadoApuntado 
	// 4) nombreActual 
	// 5) nombreApuntado
	
	// Nota: no confundir estadoXXXXX con nombreXXXXX, el primero hacer referencia si es 0, 1, 2 o 3, mientras que el segundo
	// a su nombre (q0, s2, t1)

	// Entonces, los que realmente me van a conducir por todo el automata/grafo, seran los i...
	// Supongamos que se tiene las siguientes transiciones: q0: 0 ->  q2
	//														q2: 1 ->  q1
	// Ahi se estad diciendo que hay una transicion (con valor 0) que va desde q0 a q2 (recordar lo de grafo dirigido,
	// solo va en una direccion). Asi que, si queremos ir de q0 a q2, en la *matriz de adyacencia* se debe hacer lo siguiente:
	// 1) Buscar a q0 en la fila i
	// 2) buscar a q2 en la colmuna que corresponde con q1.
	// 3) Una vez encontrado la posicion que corresponde a la ubicacion para dicha transicion, toca hacer un cambio.

	//														NEA  T	  NES
	// 4) Como ya se conoce el Nombre del Estado Siguiente (q0: 0 ->  q2), en la fila i pasaremos de q0 a q2. Por lo que
	//    ahora q2 sera el nuevo Nombre de Estado Actual. Es decir, al valor i que un principio era i = 0, se lo cambia por i = 2
	
	bool isCadenaValida(std::string cadena) { // Ejemplo de cadena a validar: 110
		// Se guarda el nombre del estado anterior. Al principio, antes de recorres, el estado anterior por defecto tiene
		// que ser el inicial
		std::string auxAnterior = nombreEstadoInicial;

		// Variable de verificacion
		bool isCorrecto = false;

		// Contador para la cadena a verificar
		int x = 0;

		for (int i = 0; i < numeroEstados; i++, x++) {
			for (int j = 0; j < numeroEstados; j++) {
				// Si ya se recorrio la cadena y el estado actual es un terminal (o es terminal e inicial a la vez, 3)
				// quiere decir que se verifico con exito la cadena. Esta condicion depende de las otras, sino nunca entrara.
				if (x == (int)cadena.length() && (matrizAdyacencia[i][j].estadoActual == '1' || matrizAdyacencia[i][j].estadoActual == '3') && isCorrecto) {
					return true;
				}
				// Si ya se recorrio la cadena y ademas el estado actual es un no terminal (o ninguno, 2) quiere decir que la cadena
				// es invalida. Esta condicion tambien depende de las otras, sino nunca entrara.
				else if (x == (int)cadena.length() && (matrizAdyacencia[i][j].estadoActual == '0' || matrizAdyacencia[i][j].estadoActual == '2')) {
					return false;
				}
				// Siguiendo el ejemplo (110)...
				// Para que entre por primera vez a esta zona, la posicion debe ser [0][0]
				// Y, debido a que en cada posicion se almacena un struct Estado, se puede conocer los siguientes datos:
				// 1) transiciones
				// 2) estadoActual
				// 3) estadoApuntado
				// 4) nombreActual
				// 5) nombreApuntado
				// Al principio auxAnterior = nombreEstadoInicial, por lo que si llegara a entrar
				// Ademas, se debe verificar que en el vector de transiciones de [0][0], exista cadena[0], es decir, se debe buscar coincidencias.
				else if (matrizAdyacencia[i][j].nombreActual == auxAnterior && buscarCoincidencia(matrizAdyacencia[i][j].transicion, cadena[x], isCorrecto)) { //
					// La condicion siguiente es por si en el automata se declaran valores nulos (epsilon).
					// Asi que primero se verifica que no haya epsilons.
					// Lo normal es que en el vector de transiciones donde se encuentre epsilon, este sea el unico valor ahi
					if (matrizAdyacencia[i][j].transicion[0] != '\0') {
						// Se almacena el nombre actual en auxAnterior para el siguiente recorrido
						auxAnterior = matrizAdyacencia[i][j].nombreApuntado;
						// Se hace el salto o adelantamiento en i. Pero, se le resta -1, pues se esta zona hara que se salga del for j
						// por lo que cuando i regrese, su for hara un incremento que no deseamos. Queremos que se mantenga el valor
						i = atoi(matrizAdyacencia[i][j].nombreApuntado.substr(1, matrizAdyacencia[i][j].nombreApuntado.length() - 1).c_str()) - 1;
						// Hasta este momento se verifico con exito el primer caracter, por tanto true
						isCorrecto = true;
						// Salimos del for j
						j = numeroEstados;
					}
					// Si existen un epsilon, hacemos un retroceso en x (contador de la cadena a verificar), pues el valor epsilon no se
					// puede representar en la cadena y tambien para que el for i, no lo incremente
					else {
						auxAnterior = matrizAdyacencia[i][j].nombreApuntado;
						i = atoi(matrizAdyacencia[i][j].nombreApuntado.substr(1, matrizAdyacencia[i][j].nombreApuntado.length() - 1).c_str()) - 1;
						x--;
						j = numeroEstados;
					}
				}
				// Si es que la cadena hubiese sido 310, el 3 no existe en el automata. Entonces, para evitar que x avance y entre a
				// la linea 256 se debe hacer retrocederlo. De esta forma recorrera la matriz y nunca sera marcado como true, solo
				// retornara el valor que hay inicialmente en isCorrecto, que es false.
				else if (auxAnterior == nombreEstadoInicial && j == numeroEstados - 1) {
					x = -1;
				}
			}
		}
		return isCorrecto;
	}

	// Para determinar si la contraseña es de minimo 5 caracteres o 20 como maximo
	bool extension(std::string password) {
		if (password.length() >= 5 && password.length() <= 20)
			return true;
		else
			return false;
	}

	bool isPasswordValido (std::string password) {// Aa1_!
		char caracteresEspeciales[28];
		char mayusculas[26]				= {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 
			'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		char minusculas[26]				= {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
			'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		char numeros[10]				= {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
		
		// Para llenar los caracteres al array caracteresEspeciales[]
		for (int i = 33; i <= 47; i++)
			caracteresEspeciales[i - 33] = i;
		
		for (int i = 58; i <= 64; i++)
			caracteresEspeciales[i - 43] = i;
		
		for (int i = 91; i <= 96; i++)
			caracteresEspeciales[i - 69] = i;
		
		caracteresEspeciales[28] = '\0';
		
		int contMayusculas = 0, contMinusculas = 0, contNumeros = 0, contEspeciales = 0;
		
		for (int i = 0; i < (int) password.length(); i++) {
			if (buscar(password[i], mayusculas, 26))
				++contMayusculas;
			if (buscar(password[i], minusculas, 26))
				++contMinusculas;
			if (buscar(password[i], numeros, 10))
				++contNumeros;
			if (buscar(password[i], caracteresEspeciales, 28))
				++contEspeciales;
		}
		
		if (contMayusculas > 0 && contMinusculas > 0 && contNumeros > 0 && contEspeciales > 0)
			return true;
		else
			return false;
	}
};

int main() {
	// Creando el automata de dos estados
	AutomataFD* afd = new AutomataFD(2);
	
	// Definiendo el estado inicial
	afd->estadoInicial("q0");
	// Definiendo el estado terminal
	afd->estadoFinal("q1");
	
	// Agregando las transiciones
	afd->agregarTransicion("q0", '\0', "q1");
	
	for (int i = 33; i <= 122; i++)
		afd->agregarTransicion("q1", i, "q1");
	
	std::string password;
	
	// Encontrando la ruta relativa donde se ubica el .exe
	char buffer[MAX_PATH];
	GetModuleFileName( NULL, buffer, MAX_PATH );
	std::string fileName = std::string(buffer);
	// Encontrando la ruta relativa del directorio (carpeta) donde se ubica el .cpp
	std::string directory = fileName.substr(0, fileName.find_last_of("\\/")); // get path directory
	
	// Establenciendo el lugar donde se guardara el archivo txt
	std::string fileCadena = directory + "/cadena.txt";
	std::ifstream cadena(fileCadena, std::ios::in);
	
	// Verificando que el archivo cadena.txt este abierto
	// Se guarda el contenido del txt en PASSWORD y se elimina el archivo 
	// cadena.txt para evitar concatenaciones innecesarias que pueda hacer JAVA
	if (cadena.is_open()) {
		std::getline(cadena, password);
		cadena.close();
		std::remove(fileCadena.c_str());
	} else {
		std::cout << 0;
		return 0;
	}

	// verificando que cumpla con la extension minima y maxima (5 - 20)	 
	if (afd->extension(password)) 
		// verficando que la cadena sea valida para el automata
		if (afd->isCadenaValida(password)) 
			// verificando que la contraseña contenga como minimo 1 numero, 1 mayuscula, 1 minuscula y 1 caracter especial
			if (afd->isPasswordValido(password)) 
				std::cout << 1;
			else
				std::cout << 0;
		else
			std::cout << 0;
	else
		std::cout << 0;

	return 0;
}
