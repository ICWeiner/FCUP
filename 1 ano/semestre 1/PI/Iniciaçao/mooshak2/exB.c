#include <stdio.h>

int main(){
	int r,
		g,
		b,
		n,
		r2,
		g2,
		b2,
		i=0;

	scanf("%d %d %d %d",&r,&g,&b,&n);

	for(;n>0;n--){
		scanf("%d %d %d",&r2,&g2,&b2);

		if( (r==r2) && (g==g2) && (b==b2))
			i++;
	}
	printf("%d\n", i);

}