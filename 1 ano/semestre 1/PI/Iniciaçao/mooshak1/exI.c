#include <stdio.h>

int main(){
	int starter,counter=1,i=1,curr,next;

	scanf("%d",&starter);
	curr=starter;

	while(curr!=0){
		scanf("%d",&next);

		if((next - curr)==1){
			i++;
		}else if (counter<i){
			counter=i;
			starter=curr-counter+1;
			i=1;
		}else{
			i=1;
		}
		curr=next;

	}
	printf("%d %d\n",starter,counter);
}