#include <stdio.h>

void inverter(char *str){
	int size=1;


	while(*str!='\0'){
		size++;
		str++;
	}

	char reverse[size],*rptr=reverse;


	for(int i = size; i > 0; i--){
		str--;
		*rptr= *str;
		rptr++;
	}

	*rptr='\0';

	rptr=reverse;

	for( int i = 0; i<=size;i++){
		printf("%c\n",*rptr);
		str++;
		*str=*rptr;
		rptr++;
	}
}

int main(){
	int size = 5;
	char str[] ={'a','b','c','d','\0'};

	inverter(str);

	for(int i= 0;i<size;i++){
		printf("Elemento em [%d]:%c\n",i,str[i]);
	}
}