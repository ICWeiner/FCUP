// -----------------------------------------------------------
// Estruturas de Dados 2020/2021 (CC1007) - DCC/FCUP
// http://www.dcc.fc.up.pt/~pribeiro/aulas/edados2021/
// -----------------------------------------------------------
// Geracao de permutacoes
// Ultima alteracao: 10/05/2019
// -----------------------------------------------------------

import java.util.Scanner;

public class ED202 {
   static float min=100;
   // Escrever todos as permutacoes do array v[]
   static void permutations(float v[]) {
      boolean used[] = new boolean[v.length]; // $i$ esta na permutacao?
      int perm[] = new int[v.length];         // permutacao actual
      goPerm(0, v, used, perm); // chamar funcao recursiva
      }
      // Gera todos os subconjuntos a partir da posicao 'cur'
   static void goPerm(int cur, float v[], boolean used[], int perm[]) {
      if (cur == v.length) {  // Caso base: terminamos a permutacao
         float value = 0;
         for (int i=0; i<v.length; i++) // Escrever a permutacao
            value += v[perm[i]];
         min = Math.min(min,value);
         //System.out.print(v[perm[i]] + " ");
         System.out.println(value);
         } else { // Se nao terminamos, continuar a gerar
            for (int i=0; i<v.length; i++) // Tentar todos os elementos
               if (!used[i]) { 
                  used[i] = true; perm[cur] = i;
                  goPerm(cur+1, v, used, perm);
                  used[i] = false;
               }
            }
         }

   // -----------------------------------------------------------
   
   public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
      int n = in.nextInt();
      String nomes[] = new String[n];
      float v[][] = new float[n][n];

      for(int i = 0; i < n; i++)
         nomes[i] = in.next();

      for(int i = 0; i < n; i++)
         for(int j = 0; j < n; j++ )
            v[i][j] = in.nextFloat();

      for(int i = 0; i < n; i++)
         permutations(v[i]);

      System.out.println(min);
   }
}
