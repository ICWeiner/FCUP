#include <stdio.h>

int mdc(int num, int den);
int modulo(int a);


struct Frac{
  int num;
  int den;
  int sinal;
  int erro;
};
typedef struct Frac FRAC;

FRAC simp(FRAC f);

FRAC soma(FRAC a, FRAC b);

FRAC sub(FRAC a, FRAC b);

FRAC mult(FRAC a, FRAC b);

FRAC div(FRAC a, FRAC b);

int main(){

  int numerador, denominador;
  FRAC input;
  FRAC input2;
  FRAC output;
  //Sinal : -1, Num: 1,Den: 4, Erro: 0 (não tem erro)
  
  
  scanf("%d %d", &input.num, &input.den);
  scanf("%d %d", &input2.num, &input2.den);

  output = soma(input, input2);
  


  printf("Sinal: %d, Num: %d, Den: %d, Erro: %d\n ", output.sinal ,output.num, output.den,output.erro);

  

  return 0;
}


int mdc(int num, int den){
  if(den > num) return mdc(den, num);
  else if (den == 0) return num;
  else return mdc(den, num%den);
}

int modulo(int a){
  return a > 0 ? a : -a;

}

FRAC soma(FRAC a, FRAC b){

    FRAC aux;
    int numa_aux=a.num, dena_aux=a.den;
    int numb_aux=a.num, denb_aux=a.den;

    if(a.num * a.den < 0){ //Transforma o sinal
      if(a.num < 0){
        numa_aux = -modulo(a.num);
        dena_aux = modulo(a.den);
      }
    } else{
      numa_aux = modulo(a.num);
      dena_aux = modulo(a.den);
    }

    if(b.num * b.den < 0){ //Transforma o sinal
      if(a.num < 0){
        numb_aux = -modulo(b.num);
        denb_aux = modulo(b.den);
      }
    } else{
      numb_aux = modulo(b.num);
      denb_aux = modulo(b.den);
    }

    numa_aux *= denb_aux;
    dena_aux *= denb_aux;
    numb_aux *= dena_aux;
    denb_aux *= dena_aux;


  numa_aux += numb_aux;

  aux.num = numa_aux;
  aux.den = denb_aux;

  return simp(aux);


  

}

FRAC simp(FRAC f){
    FRAC aux;
    int num_aux, den_aux;
    if(f.num < 0) num_aux = -f.num;
    else num_aux = f.num;
    if(f.den < 0) den_aux = -f.den;
    else den_aux = f.den;

    int divide = mdc(num_aux, den_aux);
    if(f.den == 0) aux.erro = 1; // Determina o erro
    else aux.erro = 0;


    if(f.num * f.den < 0) aux.sinal = -1; //Determina o sinal
    else aux.sinal = 1;

    aux.num = num_aux / divide;
    aux.den = den_aux / divide;

    return aux;
}


