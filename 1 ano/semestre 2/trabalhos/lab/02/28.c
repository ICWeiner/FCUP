#include <stdio.h>

void reduzir(int *pnum, int *pdenom){

	int t,denom=*pdenom,num=*pnum;

	while(denom != 0){
		t = denom;
		denom = num % denom;
		num=t; 
	}

	*pdenom=*pdenom/num;
	*pnum=*pnum/num;

}

int main(){
	int num,denom;

	num=1071;
	denom=462;

	reduzir(&num,&denom);

	printf("%d %d\n",num,denom);

	return 1;
}