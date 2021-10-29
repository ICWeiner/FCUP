#include <stdio.h>

int main(){
	int i,
		x,
		y,
		z,
		n=0;

	scanf("%d",&i);
	scanf("%d",&x);
	scanf("%d",&y);

	for(;i>0;i--){
		scanf("%d",&z);

		if( ((x*2)<y) && (z*2)<y)
			n++;
		x=y;
		y=z;
	}
	printf("%d\n",n);
}