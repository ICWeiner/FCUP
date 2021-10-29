#include <stdio.h>

int main(){
	signed int x,
	y,
	value,
	sum=0,
	d,
	all=1;

	scanf("%d",&d);

	scanf("%d",&x);

	scanf("%d",&y);

	for(int i=2;i<d;i++){

		scanf("%d",&value);

		if (value!=x && value!=y){
			if (value>x || -value<-x || value%y==0){
				sum+= value;
				all=0;
			}
		}
		
	}

	if (all==0){
		printf("Resposta G - soma: %d\n",sum);
	}else{
		printf("Resposta G - nenhum valor satisfaz\n");
	}
	
}