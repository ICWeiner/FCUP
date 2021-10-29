// -----------------------------------------------------------
// Estruturas de Dados 2020/2021 (CC1007) - DCC/FCUP
// http://www.dcc.fc.up.pt/~pribeiro/aulas/edados2021/
// -----------------------------------------------------------
// Invertendo um array (versao recursiva)
// Ultima alteracao: 21/04/2018
// -----------------------------------------------------------

import java.util.Arrays;
https://www.youtube.com/watch?v=FZEO4_krHCQ
public class TestReverse {

   // Inverter array v entre posicoes start e end
   static void reverse(int v[], int start, int end) {
      if (start>=end) return;     // Caso base: array de tamanho < 2
      int tmp = v[start];         // Trocar primeiro com ultimo
      v[start] = v[end];
      v[end] = tmp;
      reverse(v, start+1, end-1); // Chamada recursiva para o resto
   }

   // Inverter array v entre posicoes start e end
   static boolean capicua(int v[], int start, int end) {
      if (start>=end) return true;     // Caso base
      if (v[start] != v[end]) return false;
      capicua(v, start+1, end-1); // Chamada recursiva para o resto
      return true;
   }
   
   // -----------------------------------------------------------
   
   public static void main(String[] args) {
      int v[] =  {1,2,3,2,1}; // Inicializacao de array
      int v1[] = {1,2,3,1};

      System.out.println("[1,2,3,2,1] " + capicua(v, 0, 4));
      System.out.println("[1,2,3,1] " + capicua(v, 0, 3));
   }
}
