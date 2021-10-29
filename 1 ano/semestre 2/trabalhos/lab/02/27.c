#include <stdio.h>

void max_min(int vec[], int size, int *pmax, int *pmin){

	*pmax=*pmin=vec[0];

	for(int i = 0; i < size; i++){
		if(*pmax<vec[i])
			*pmax=vec[i];
		else if(*pmin>vec[i])
			*pmin=vec[i];
	}

}

void main(){
	int vec[]={3,56,9,1,54,0},max,min;

	max_min(vec,6,&max,&min);

	printf("max:%d\nmin:%d\n",max,min);

}