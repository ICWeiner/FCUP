#include <stdio.h>

int main(){
	int c,
		i,
		j,
		s,
		t;

	scanf("%d",&c);

	for(;c>0;c--){
		scanf("%d %d ",&i,&j);
		scanf("%d %d ",&s,&t);

		if( (s<=i) && (t>=j) )
			printf("Com alibi\n");
		else
			printf("Sem alibi\n");
		
	}
	

}