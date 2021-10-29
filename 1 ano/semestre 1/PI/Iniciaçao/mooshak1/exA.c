#include <stdio.h>
#include <stdlib.h>

int main(){
	int currFloor,
		elev1,
		elev2;

	scanf("%d %d %d",&currFloor,&elev1,&elev2);

	if((elev1!=999) && (elev2!=999) && (elev2!=elev1)){

		int distance1=abs(currFloor - elev1),
			distance2=abs(currFloor - elev2);

		if( distance1 != distance2 ){
			if (distance1<distance2){
				printf("1");
			}else{
				printf("2");
			}
		}else{
			if(elev1>elev2){
				printf("1");
			}else{
				printf("2");
			}
		}
	}else if(elev1==elev2){
		printf("1");
	}else if((elev1==999) && (elev2!=999)){
		printf("2");
	}else if((elev1!=999) && (elev2==999)){
		printf("1");
	}else{
		printf("0");
	}
}