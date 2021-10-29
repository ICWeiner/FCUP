#include <stdio.h>

void main(){
	int x[3]={23,41,17};

	printf("%p\n",x[0]);

	printf("%p\n",&*(x[0]));
}