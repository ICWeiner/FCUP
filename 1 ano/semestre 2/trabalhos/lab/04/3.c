#include <stdio.h>
#include <stdlib.h>

int* readarray(int n){
	 int i = 0;
	 int* v = (int*) malloc(sizeof(n*sizeof(int)));

	 for (i = 0; i<n; i++){
	 	scanf("%d",v + i);
	 }

	 return v;
}

int* somaarrays(int *a, int *b, int n) {
 	int i;
	int* v = (int*) malloc(sizeof(n*sizeof(int)));

	for( i = 0; i < n; i++){
		*(v + i) = *(a + i) + *(b + i);
	}

	return v;
}

void printarray(int *v, int n){
	
	for(int i = 0; i < n; i++){
		printf("%d\n", *(v + i));
	}
}

int main(){
	int n;
	int *va, *vb, *vr;

	scanf("%d", &n);

	va = readarray(n);

	vb = readarray(n);	

	vr = somaarrays(va, vb, n);

	//printarray(va, n);

	//printarray(vb, n);

	printarray(vr, n);

	//free(va);
	//free(vb);
	//free(vr);

	return 0;
}