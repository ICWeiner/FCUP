#include <stdio.h>
#include <stdlib.h>

FILE* abre_fich(char* s);
char* palavra(FILE *f);
void mensagem(char* m);


int main(int argc, char* argv[]){
	FILE *fich;
	char *pal;
	if (argc != 2){
		mensagem("Uso: ./palavras ficheiro");
	}
	fich = abre_fich(argv[1]);
	while((pal = palavra(fich)) != NULL){
		printf(" %s\n", pal);
		free(pal);
	}
}

FILE* abre_fich(char* s){
    FILE *fptr;
    if ((fptr = fopen(s, "r")) == NULL) {
        printf("Não foi possivel abrir o ficheiro");
        return NULL;
    }
    return fptr;
}

char* palavra (FILE *f){
	int lettercount = 0;
	char texto[50],palavra[50];
	fgets(texto, 50, f);

	for (int i = 0; texto[i] != '\0'; i++){
		printf("%c\n",texto[i]);
		if((texto[i] >= 'a' && texto[i] <= 'z') || (texto[i] >= 'A' && texto[i] <= 'Z')){
			palavra[lettercount] = texto[i];
			lettercount++;
		}
	}

	if (lettercount < 2)
	{
		return NULL;
	}

	char *ptr = (char*) malloc(sizeof(lettercount) * sizeof(char));

	for (int i = 0; i < lettercount; i++)
	{

		*(ptr + i) = palavra[lettercount];
	}

	return ptr;
}

void mensagem(char* m){
	printf("%s\n", m);
	exit(0);
}
