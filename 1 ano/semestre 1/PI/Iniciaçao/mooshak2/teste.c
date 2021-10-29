#include <stdio.h>

int main(){
	int k, t,n;
	k = 1;
	scanf("%d",&n);
	for(t=2; t*t < n; t++)
		if (n%t == 0){
			printf("k=%d,t=%d\n",k,t);
			k += 2;
			
		}
	if (t*t == n) k++; 
	printf("k=%d,t=%d n=%d",k,t,n);
}

