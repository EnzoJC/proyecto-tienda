#include <iostream>
#include <cstring>
//no se usara using namespace std;

//La funcion comvierte un numero entero a un char. Por ejemplo
//128 lo convierte a "128"
char* intToArrayChar(int N) 
{ 
	int m = N; 
	int digit = 0; 
	while (m)
	{ 
		digit++; 
		m /= 10; 
	} 
	char *arr = new char[digit];
	char *arr1 = new char[digit]; 
	
	int index = 0; 
	while (N)
	{ 
		arr1[++index] = N % 10 + '0'; 
		N /= 10; 
	} 
	
	int i; 
	for (i = 0; i < index; i++) 
	{ 
		arr[i] = arr1[index - i]; 
	} 
	arr[i] = '\0'; 
	
	return arr;
} 

//La funcion adelanta 13 letras del abecedario. Por ejemplo
//A + 13 = N
void toRot13(char *texto)
{
	while (*texto != '\0')
	{
		if (*texto >= 'A' && *texto <= 'M')
		{
			*texto = *texto + 13;
			texto++;
		}
		else if (*texto >= 'O' && *texto <= 'Z')
		{
			*texto = *texto - 13;
			texto++;
		}
		else if (*texto >= 'a' && *texto <= 'm')
		{
			*texto = *texto + 13;
			texto++;
		}
		else if (*texto >= 'o' && *texto <= 'z')
		{
			*texto = *texto - 13;
			texto++;
		}
		else if (*texto = ' ')
		{
			*texto = 32;
			texto++;
		}
	}
}

//La funcion convierte cada caracter a su respectivo numero ASCII
//lo que retorna es un arreglo de esos numeros ASCII
char* arrayCharToArrayCharASCII(char* textoConvertido)
{
	char *acum = new char[100];
	for(int i = 0; i < strlen(acum); i++)
	{
		acum[i] = '\0';
	}
	while (*textoConvertido != '\0')
	{
		char* c = intToArrayChar(*textoConvertido);
		textoConvertido++;
		strcat(acum, c);
		if(*textoConvertido != '\0' )
		{
			strcat(acum, "-");
		}
	}
	return acum;
}

int main()
{
	char texto[] = "HOLA";	// UBYN -> 85-66-89-78
	toRot13(texto);
	char* a = arrayCharToArrayCharASCII(texto);
	std::cout << texto << std::endl;
	std::cout << a << std::endl;
	return 0;
}
