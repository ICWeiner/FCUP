#include <stdio.h>
#include <stdlib.h>

FILE* abre_fich(char* s);
void mensagem(char* m);

int main(int argc, char* argv[]){
	FILE *fich;
	char *pal;
	if (argc != 4){
		mensagem("Uso: ./mat2file linhas colunas ficheiro");
	}

	fich = abre_fich(argv[3]);
}

FILE* abre_fich(char* s){
    FILE *fptr;
    if ((fptr = fopen(s, "r")) == NULL) {
        printf("Não foi possivel abrir o ficheiro");
        return NULL;
    }
    return fptr;
}

void mensagem(char* m){
	printf("%s\n", m);
	exit(0);
}
