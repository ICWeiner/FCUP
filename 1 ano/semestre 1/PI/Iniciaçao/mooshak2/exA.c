#include <stdio.h>

int main(){
	int s,
		t,
		i;

	scanf("%d %d %d",&s,&t,&i);

	if(s>t){
		if (s>= (t+(i*3)))
			printf(":-D\n");
		else
			printf(":-)\n");
	}else{
		if (t >= (s+(i*3)))
			printf(":-(\n");
		else
			printf(":-|\n");
	}
}