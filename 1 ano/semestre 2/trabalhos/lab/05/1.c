#include <stdio.h>
#include <stdlib.h>

int main(int argc,char* argv[]){
	char text[50];

	FILE* fptr = fopen (argv[2], "r"), *outptr = fopen(argv[3],"w");

	fgets(text, 50, fptr);
	
	char* textptr = text;

	int i = 0;

	while(*(textptr + i) != '\0' ) i++;

	char out1[] = "O ficheiro tem " , out2[] = " caracteres";

	fputs(out1,outptr);
	fputc(i,outptr);
	fputs(out2,outptr);


}