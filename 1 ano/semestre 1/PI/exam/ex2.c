#include <stdio.h>

int lerLinha(char lim[]){
	int i=0;
	

	lim[i]=getchar();

	while(lim[i]!='\n'){
		i++;
		lim[i]=getchar();
	}

	lim[i]=='\0';

	return i; 
}

char analisaLinha(char lim[]){
	int i,j=-1,n;

	for(i=0;i<100;i++){
		if (lim[i]==':'){
			if(j==-1)
				j=i;
			else 
				j==-2;
		}else if(lim[i]=='\0')
			n=i;
	}

	printf("%d %d \n",j ,n );

	if (j<=-1)
		return 'N';
	else if(j>(n-j-1))
		return 'E';
	else if(j<(n-j-1))
		return 'D';
	else if(j==(n-j-1)){
		int same =1;
		for(i=0;i<3;i++){
			if(lim[i]!=lim[i+1+j]){
				same==0;
			}
		}
		if(same==0)
			return 'C';
		else
			return 'I';
	}


}

int posicao(char lim[], char c){
	int i,j=-1;

	for(i=0;i<7;i++){
		if(lim[i]==c){
			if(j==-1)
				j=i;
			else
				j=-2;
		}
	}

	if (j==-2 || j==-1)
		return -1;
	else
		return j;
	

}

void main(){
	char lim[8],letter;
	int readChars,posicaoCarater;

	readChars=lerLinha(lim);

	printf("%s\ncharacteres lido:%d\n",lim,readChars);

	posicaoCarater==posicao(lim,'2');

	if(posicaoCarater==(-1))
		printf("O caracter ou não aparece nenhuma vez ou esta repetido\n");

	letter= analisaLinha(lim);

	printf("%c\n",letter);
}