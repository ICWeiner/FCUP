#include <stdio.h>

typedef struct 
{
	int ano;
	int mes;
	int dia;
	
}DATA;

int compara(DATA d1, DATA d2){
	if (d1.ano==d2.ano){
		if (d1.mes==d2.mes){
			if (d1.dia==d2.dia){
				return 0;
			}else if (d1.dia>d2.dia){
				return 1;
			}else{
				return 0;
			}
		}else if(d1.mes>d2.mes){
			return 1;
		}else{
			return -1;
		}
	}else if (d1.ano>d2.ano){
		return 1;
	}else{
		return -1;
	}
}

void main(){
	DATA d1={1998,10,21}, d2={2019,02,7}, d3={2021,02,7};
	int result;

	result=compara(d3,d3);

	printf("%d\n",result);
}