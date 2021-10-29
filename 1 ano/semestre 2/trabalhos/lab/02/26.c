#include <stdio.h>

void decompor(int total_seg, int *horas, int *mins, int *segs){

	int total_seg_temp=total_seg,horas_temp=0,mins_temp=0;



	while(total_seg_temp>=3600){
		total_seg_temp-=3600;

		horas_temp++;
	}


	while(total_seg_temp>=60){
		total_seg_temp-=60;
		mins_temp++;
	}

	*horas=horas_temp;
	*mins=mins_temp;
	*segs=total_seg_temp;
}

void main(){
	int total_seg,h=0,m=0,s=0,*ph=&h,*pm=&m,*ps=&s;

	scanf("%d",&total_seg);

	decompor(total_seg,ph,pm,ps);

	//printf("%d %d %d  \n",ph,pm,ps);

	printf("Em %d ha %d horas %d mins e %d segs\n",total_seg,h,m,s);

}