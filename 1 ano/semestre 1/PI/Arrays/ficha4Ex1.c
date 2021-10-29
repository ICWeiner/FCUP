#include <stdio.h>

void ler_seq(int[] ,int );

void escrever_seq(int[] , int );

void copiar(int[] ,int[] , int );

int media_arred(int[] ,int );


int main(){
	int n,m,a[1000],b[1000],avg;

	printf("\nDigite o numero de sequencias e os seus comprimentos\n");
	scanf("%d  %d", &m ,&n);

	ler_seq(a,n);

	copiar(b,a,n);

	avg=media_arred(b,n);

	for(int i=1; i<m;i++){
		int avgTemp;
		ler_seq(a,n);

		avgTemp=media_arred(a,n);

		if(avgTemp>avg){
			avg=avgTemp;
			copiar(b,a,n);

		}
	}

	escrever_seq(b,n);
	printf("media = %d\n", avg);
}

void ler_seq(int x[], int n){
	int i;
	for(i= 0;i<n;i++){
		scanf("%d",&x[i]);
	}
}

void escrever_seq(int x[], int n){
	printf("%d",x[0] );
	for(int i=1;i<n;i++){
		printf(" %d",x[i]);
	}
	printf("\n");
}

void copiar(int y[],int x[], int n){
	for(int i=0;i<n;i++){
		y[i]=x[i];
	}
}

int media_arred(int x[],int n){
	int sum=0,sumFinal,i;


	for(i=0;i<n;i++){
		sum+=x[i];
	}

	sumFinal = (float) ((sum*1.0)/n)+0.5;

	return sumFinal;
}