#include <stdio.h>
#include <stdlib.h>

int main(int argc,char* argv[]){
	int sum = 0;

	argv++;

	for (int i = 0; i < (argc - 1); i++)
	{
		sum += atoi(*argv);
		argv++;
	}

	printf("%d\n", sum);
}