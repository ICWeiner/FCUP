#include <stdio.h>

int contar_espacos(char *str){
	int k=0;

	while (1==1){
		if (*str == ' ')
			k++;
		else if (*str=='\0'){
			return k;
		}
		str++;
	}
}

int main(){
	char str[5] ={'O','l',' ','a','\0'};
	int espacos;

	espacos=contar_espacos(str);

	printf("Havia %d espacos\n", espacos );
}