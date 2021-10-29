import java.util.Scanner;
import java.util.Random;

class Matrix {
   int data[][]; // os elementos da matriz em si
   int rows;     // numero de linhas
   int cols;     // numero de colunas

   // construtor padrao de matriz
   Matrix(int r, int c) {
      data = new int[r][c];
      rows = r;
      cols = c;
   }

   Matrix(int r, int c,int min, int max){
      rows = r;
      cols = c;
      data = new int[r][c];
      Random rand = new Random();

      for(int i = 0; i < rows; i++){
         for(int j = 0; j < cols; j++){
            int number = rand.nextInt(max);
            while( number < min)
               number = rand.nextInt(max);
            data[i][j] = number;
         }   
      }
   }

   public static Matrix identity(int n){
      Matrix identity = new Matrix(n,n);

      for(int i = 0 ; i < n; i++){
         identity.data[i][i] = 1;
      }
      return identity;
   }

   public Matrix transpose(){
      Matrix matrix = new Matrix (cols,rows);

      for (int i=0; i<rows; i++){
         for (int j=0; j<cols; j++){
            matrix.data[j][i]= this.data[i][j];
         }
      }
      return matrix;
   }

   public Matrix sum(Matrix m){
      Matrix outMatrix = new Matrix(m.rows,m.cols);

      for (int i=0; i<rows; i++){
         for (int j=0; j<cols; j++){
            outMatrix.data[i][j] = this.data[i][j] + m.data[i][j];
         }
      }
      return outMatrix;
   }

   public Matrix multiply(Matrix m){
      Matrix outMatrix = new Matrix(this.rows, m.cols);
      for (int i = 0; i < outMatrix.rows; i++){
         for (int j = 0; j < outMatrix.cols; j++){
            for(int k = 0; k < this.cols;k++){
               outMatrix.data[i][j] += this.data[i][k] * m.data[k][j];
            }
         }
      }
      return outMatrix;
   }


   // Ler os rows x cols elementos da matriz
   public void read(Scanner in) {
      for (int i=0; i<rows; i++)
         for (int j=0; j<cols; j++)
            data[i][j] = in.nextInt();
   }

   // Representacao em String da matriz
   public String toString() {
      String ans = ""; 
      for (int i=0; i<rows; i++) {
         for (int j=0; j<cols; j++)
            ans += data[i][j] + " ";
         ans += "\n";
      }
      return ans;
   }   
}