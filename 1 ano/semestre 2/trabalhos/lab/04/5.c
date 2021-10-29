#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* my_strncat(char *dest, char *src, int n);

char* my_strdup(char *s);

int main(){
	char s1[]="abc" ,s2[]="def";

	s1 = my_strncat(s1,s2,2);

	printf("%s\n",s1 );
}

char* my_strncat(char *dest, char *src, int n){
	int sizedest = 0, sizesrc = 0;
	int i = 0;

	while ( *( dest + i) != '\0'){
		i++;
		sizedest++;
		if (*( src + i) != '\0' ) sizesrc++;
	}

	char *ptr = (char*) malloc(sizeof(sizedest + n) *sizeof(char));

	if (sizesrc > n) n = sizesrc;

	for(int j = 0; j < sizedest; j++) *( ptr + j ) = *(dest + j);
	for(int k = sizedest, i = 0; k < (n + sizedest); k++, i++) *( ptr + k ) = *(dest + i);

	return ptr;
}