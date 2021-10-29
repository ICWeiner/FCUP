#include <stdio.h>

int main(){
	int lixo=-2
		,azul=0
		,amarelo=0
		,verde=0;


	scanf("%d",&lixo);

	while (lixo!=-1){

		if (lixo>0 && lixo<11){
			azul++;
		}else if (lixo<24){
			verde++;
		}else if (lixo<41){
			amarelo++;
		}
		scanf("%d",&lixo);

	}

	printf("azul: %d\n",azul);
	printf("amarelo: %d\n",amarelo); 
	printf("verde: %d\n",verde);
}