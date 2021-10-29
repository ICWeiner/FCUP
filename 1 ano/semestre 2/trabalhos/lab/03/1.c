#include <stdio.h>
#include <math.h>
struct Ponto{
    int x;
    int y;
}; typedef struct Ponto PONTO;

int distanciaEuclideana(PONTO p1, PONTO p2);
PONTO soma(PONTO p1, PONTO p2);

int main(){

    PONTO p1, p2, p3;
    
    int dist;
    scanf("%d %d", &p1.x, &p1.y); // ler coordenada x e y de um ponto para p1
    scanf("%d %d", &p2.x, &p2.y); // ler coordenada x e y de um ponto para p2
    
    dist = distanciaEuclideana(p1,p2);
    
    p3 = soma(p1,p2);
    
    printf ("Distância: %d\n", dist); // imprimir ditância euclideana entre os pontos p1 e p2
    
    printf ("Soma: (%d,%d)\n", p3.x, p3.y);  // imprimir vector soma dos vectores representadospelos pontos p1 e p2
    
    return 0;
    
}

int distanciaEuclideana(PONTO p1, PONTO p2){
  return sqrt((p1.x - p2.x)*(p1.x - p2.x) + (p1.y-p2.y)*(p1.y-p2.y));
}

PONTO soma(PONTO p1, PONTO p2){
  PONTO p3;
  p3.x = (p1.x + p2.x);
  p3.y = (p1.y + p2.y);
  return p3;
}
