#include <stdio.h>
#include <string.h>

int main(int argc, char* argv[]){
	int strcount = 0;

	argv++;

	for(int i = 0; i < (argc - 1) ; i++){
		strcount += strlen( *argv);
		printf("%s -> %lu \n", *argv, strlen( *argv));
		argv++;
	}

	printf("Total = %d\n", strcount);
}