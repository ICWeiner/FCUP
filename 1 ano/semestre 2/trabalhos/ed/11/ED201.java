// -----------------------------------------------------------
// Estruturas de Dados 2020/2021 (CC1007) - DCC/FCUP
// http://www.dcc.fc.up.pt/~pribeiro/aulas/edados2021/
// -----------------------------------------------------------
// Geracao de subconjuntos
// Ultima alteracao: 21/04/2018
// -----------------------------------------------------------

import java.util.Scanner;

public class ED201{
   private static int max = 0;
   private static int tempo;

   // Versao recursiva 2: dividir em metade esquerda e metade direita
   static int sumRec2(int v[], int start, int end) {
      if (start == end) return v[start];    // caso base (tamanho 1)
      int middle = (start + end) / 2;       // ponto medio
      int sum1 = sumRec2(v, start, middle); // recursao na metade esquerda
      int sum2 = sumRec2(v, middle+1, end); // recursao na metade direita
      return sum1 + sum2;          // combinar resultado
   }
   
   // Escrever todos os subconjuntos do array v[]
   static void sets(int v[]) {
      // array de booleanos para representar o conjunto
      boolean used[] = new boolean[v.length];
      goSets(0, v, used); // chamar funcao recursiva
   }

   // Gera todos os subconjuntos a partir da posicao 'cur'
   static void goSets(int cur, int v[], boolean used[]) {
      if (cur == v.length) {  // Caso base: terminamos o conjunto
         int sum = 0,tempMax = 0;
         //System.out.print("duracao:");
         for (int i=0; i < v.length; i++) 
            if (used[i]) sum += v[i];
         //System.out.println(sum);
         tempMax = Math.max(max,sum);
         if(tempMax < tempo) max = tempMax;
      } else {                  // Se nao terminamos, continuar a gerar
         used[cur] = true;      // Subconjuntos que incluem o elemento actual
         goSets(cur+1, v, used);// Chamada recursiva
         used[cur] = false;     // Subconjuntos que nao incluem o el. actual
         goSets(cur+1, v, used);// Chamada recursiva
      }
   }

   // -----------------------------------------------------------
   
   public static void main(String[] args) {
      //int v[] = {243,202,254,502,385,942,237,721,192}; // Inicializacao de array

      //sets(v);

      Scanner in = new Scanner(System.in);

      tempo = in.nextInt();
      int n = in.nextInt();
      int v[] = new int[n];

      for(int i = 0; i < n ; i++){
         v[i] = in.nextInt();
      }

      sets(v);

      System.out.println(max);
   }
}
