#include <iostream>
#include <string>
#include <vector>
#include <ctime>
#include <fstream>
#include <windows.h>

class AutomataFD {
private:
	//Sirve para guardar un estado (actual y siguiente), su nombre (actual y siguiente) y transicion (que apunta)
	struct Estado {
		// 0: Estado inicial, 1: Estado terminal, 2: Ninguno, 3: Ambos (EI y ET)
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
		
		void estadoInicial(std::string nombreActual) {
			nombreEstadoInicial = nombreActual;
			
			//int indice = nombreActual[1] - '0';
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
		
		void agregarTransicion(std::string nombreActual, char valorTransicion, std::string nombreApuntado) {
			int indiceActual = (int)atoi(nombreActual.substr(1, nombreActual.length() - 1).c_str());
			int indiceApuntado = (int)atoi(nombreApuntado.substr(1, nombreApuntado.length() - 1).c_str());
			
			char aux1 = matrizAdyacencia[indiceActual][indiceApuntado].estadoActual;
			char aux2 = matrizAdyacencia[indiceActual][indiceApuntado].estadoApuntado;
			std::vector<char> aux3 = matrizAdyacencia[indiceActual][indiceApuntado].transicion;
			aux3.push_back(valorTransicion);
			matrizAdyacencia[indiceActual][indiceApuntado] = Estado(aux3, aux1, aux2, nombreActual, nombreApuntado);
		}
		
		bool isCadenaValida(std::string cadena) {
			std::string auxAnterior = nombreEstadoInicial;
			Estado auxEstadoAnterior;
			bool isCorrecto = false;
			int x = 0;
			for (int i = 0; i < numeroEstados; i++, x++) {
				for (int j = 0; j < numeroEstados; j++) {
					if (x == (int)cadena.length() && (matrizAdyacencia[i][j].estadoActual == '1' || matrizAdyacencia[i][j].estadoActual == '3') && isCorrecto) {
						return true;
					}
					else if (x == (int)cadena.length() && (matrizAdyacencia[i][j].estadoActual == '0' || matrizAdyacencia[i][j].estadoActual == '2')) {
						return false;
					}
					else if (matrizAdyacencia[i][j].nombreActual == auxAnterior && buscarCoincidencia(matrizAdyacencia[i][j].transicion, cadena[x], isCorrecto)) { //
						if (matrizAdyacencia[i][j].transicion[0] != '\0') {
							auxEstadoAnterior = matrizAdyacencia[i][j];
							auxAnterior = matrizAdyacencia[i][j].nombreApuntado;
							i = atoi(matrizAdyacencia[i][j].nombreApuntado.substr(1, matrizAdyacencia[i][j].nombreApuntado.length() - 1).c_str()) - 1;
							isCorrecto = true;
							j = numeroEstados;
						}
						else {
							auxEstadoAnterior = matrizAdyacencia[i][j];
							auxAnterior = matrizAdyacencia[i][j].nombreApuntado;
							i = atoi(matrizAdyacencia[i][j].nombreApuntado.substr(1, matrizAdyacencia[i][j].nombreApuntado.length() - 1).c_str()) - 1;
							x--;
							j = numeroEstados;
						}
					}
					else if (auxAnterior == nombreEstadoInicial && j == numeroEstados - 1) {
						x = -1;
					}
				}
			}
			return isCorrecto;
		}
};

int main() {
	AutomataFD* afd = new AutomataFD(56);
	
	afd->estadoInicial("q0");
	afd->estadoFinal("q1");
	
	afd->agregarTransicion("q0", '\0', "q1");
	
	for (int i = 33; i <= 122; i++)
		afd->agregarTransicion("q1", i, "q1");
	
	std::string s;
	
	char buffer[MAX_PATH];
	GetModuleFileName( NULL, buffer, MAX_PATH );
	std::string fileName = std::string(buffer);
	std::string directory = fileName.substr(0, fileName.find_last_of("\\/")); // get path directory
	
	std::string fileCadena = directory + "/cadena.txt";
	std::ifstream cadena(fileCadena, std::ios::in);
	
	if (cadena.is_open()) {
		std::getline(cadena, s);
		cadena.close();
		std::remove(fileCadena.c_str());
	} else std::cout << 0;
	
	if (s.length() >= 5 && s.length() <= 20)
		if (afd->isCadenaValida(s)) std::cout << 1;
		else std::cout << 0;
	else std::cout << 0;
	
	return 0;
}
