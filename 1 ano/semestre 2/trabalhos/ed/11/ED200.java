// -----------------------------------------------------------
// Estruturas de Dados 2020/2021 (CC1007) - DCC/FCUP
// http://www.dcc.fc.up.pt/~pribeiro/aulas/edados2021/
// -----------------------------------------------------------
// Exemplo de flood fill
// Ultima alteracao: 21/04/2018
// -----------------------------------------------------------

import java.util.Scanner;

public class ED200 {
   static int rows;            // Numero de linhas
   static int cols;            // Numero de colunas   
   static char m[][];          // Matriz de celulas
   static boolean visited[][]; // Saber se uma dada posicao ja foi visitada

   // Tamanho da mancha que inclui posicao (y,x)
   static int t(int y, int x) {
      if (y<0 || y>=rows || x<0 || x>=cols) return 0; // Caso base: fora dos limites
      if (visited[y][x]) return 0;  // Caso base: celula ja visitada
      if (m[y][x] == '.') return 0; // Caso base: celula vazia
      int count = 1;        // celula nao vazia
      visited[y][x] = true; // marcar como visitada
      count += t(y-1, x);   // Adicionando celulas nao vizinhas
      count += t(y+1, x);   //horizontal

      count += t(y, x+1);  //vertical
      count += t(y, x-1);

      count += t(y-1, x+1);//diagonal
      count += t(y-1, x-1);
      count += t(y+1, x+1);
      count += t(y+1, x-1);
      return count;
   }
   
   // -----------------------------------------------------------
   
   public static void main(String[] args) {
      Scanner in = new Scanner(System.in);

      int n = in.nextInt();

      for(int i = 0; i < n ; i++){      
         int max = 0;

         // Leitura de uma matriz de caracteres
         rows = in.nextInt();
         cols = in.nextInt();
         m = new char[rows][cols];
         visited = new boolean[rows][cols];
         for (int j=0; j<rows; j++)
            m[j] = in.next().toCharArray();

         for(int j = 0; j < rows; j++){
            for(int k = 0; k < cols; k++){
               max = Math.max(t(j,k),max);
            }
         }System.out.println(max);
      }
   }
   
}
