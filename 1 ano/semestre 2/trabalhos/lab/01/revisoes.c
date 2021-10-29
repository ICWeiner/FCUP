#include <stdio.h>

//1.1
int contar_maiores(int vec[], int size, int val){
  int j=0;
  for(int i=0;i<size;i++){
      if (vec[i]>val) {
        j++;
      }
  }
  return j;
}

//1.3
int decimal(char str[]){
  //int numbers =/*atoi(str)*/;
  int numbers;
  return numbers;
}

//1.4
int repetidos(int vec[], unsigned size){
  
  for(int i=0;i<size;i++){
    for(int l=0;l<size;l++){
      if(i!=l && vec[i]==vec[l]){
        return 1;
      }
    }
  }
  return 0;
}

//1.5
void range(int vec[], unsigned size, int inicio, int incr){
  for(int i=0;i<size;i++){
    vec[i]=inicio+(incr*i);
  }
}


//1.6
int filtrar_positivos(int vec[],int size){
	int pos[size],neg[size],posCounter=0,negCounter=0;

	for(int i=0;i<size;i++){
		if (vec[i]>0){
			pos[posCounter]=vec[i];
			posCounter++;
		}else{
			neg[negCounter]=vec[i];
			negCounter++;
		}
	}

	for(int i=0;i<posCounter;i++){
		vec[i]=pos[i];
	}

	for(int i=0;i<negCounter;i++){
		vec[i+posCounter]=neg[i];
	}

	return posCounter;
}

//1.7
void ordenar(char str[]){
	int i=0,j=i+1;
	char swap;

	while(str[i]!='\0'){
		while(str[j]!='\0'){
			if (str[i]>str[j]){
				swap=str[i];
				str[i]=str[j];
				str[j]=swap;
				}j++;
			}i++;
			j=i+1;
		}
	}


int anagramas(char str1[],char str2[]){
	int i=0;

	ordenar(str1);
	ordenar(str2);

	while(str1[i]!='\0' || str2[i]!='\0'){
		if(str1[i]==str2[i]){
				i++;
			}
		else{
			return 0;
		}

	}return 1;
}
//1.9
int magico(int a[3][3],int n){
	int diagSum=0,laneSum=0,sum=0;

	for(int i=0;i<n;i++){
		diagSum+=a[i][i];
		for (int j=0;j<n;j++){
			laneSum+=a[i][j];
		}
		if(i==0)
			sum=laneSum;
		else if(sum!=laneSum){
			return 0;
		}
		laneSum=0;
	}
	if(diagSum==sum)
		return 1;
	else
		return 0;
}



int main(void) {
	int size=3,resultado;
	int vec[3][3] ={{2, 7 ,6},
					{9, 5 ,1},
					{4, 3 ,8}};


	resultado=magico(vec,size);

	if (resultado==0)
		printf("nao sao anagramas\n");
	else
		printf("sao anagramas\n");



  return 0;
}