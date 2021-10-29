#include <stdio.h>

void store_zeros(int *vec, int n) {
	int *ptr=vec;

	for(; ptr <( vec + n); ptr++){
		*ptr = 0;

	}
}

int main(){
	int n=6,
	vec[]={1,2,3,4,5,6};

	store_zeros(vec,n);
}