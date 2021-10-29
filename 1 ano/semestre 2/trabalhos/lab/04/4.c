#include <stdio.h>
#include <stdlib.h>
#include <string.h>


char* esp(char* s,int size);

int main(){
	char s[] = "a, Ah Ah!";

	char* s2 = esp(s,strlen(s));

	printf("%s\n",s2 );


}

char*  esp(char *s,int size){
	int i = 0;
	char* ptr = (char*) malloc(sizeof(size * sizeof(char)));
	
	while( *(s + i) != '\0' ){
		*(ptr + i) = *(s + i);
		if( *(ptr + i) < 'A' || (*(ptr + i) > 'Z'  && *(ptr + i) < 'a') || *(ptr + i) > 'z' ) *(ptr + i) = 32;
		i++;
	}

	return ptr;
	
}