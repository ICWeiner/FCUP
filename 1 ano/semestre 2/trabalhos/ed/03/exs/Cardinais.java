import java.util.Scanner;

/* -----------------------------------
  Estruturas de Dados 2020/2021
  Cardinais [ED216] 
  Feito por Diogo Nunes
----------------------------------- */

class Cardinais{
	int n;
	int m;
	int maxCount;
	int countNumber;
	char[][] data;

	Cardinais(int n, int m){
		this.n = n;
		this.m = m;
		data = new char[n][m];
	}

	public void read(Scanner stdin){
		Scanner in = stdin;

		for(int i = 0; i < n; i++){
			String buf = in.next();
			for(int j = 0; j < m; j++){
				data[i][j] = buf.charAt(j);
			}
		}
	}

	public void count(int incx, int incy){
		int x = 0;
		int y = 0;
		int currCount = 0;

		for(int i = 0; i < n; i++){
			for( int j = 0; j < m; j++){
				while()
			}
		}

		/*for ( int i = 0, xx = x, yy = y; i < n ; i++ ,xx += incx,yy += incy){
			System.out.println("n: " + n +" i:" + i + " x:" + x + " xx:" + xx + " y:" + y + " yy:" + yy + " incx:" + incx + " incy:" + incy);	
			if (xx == n || yy == m){
				y += incy;
				yy = y;
				x += incx;
				xx = x;
				System.out.println("con 1");
			}else if ( data[x][y] == data[xx][yy]){
				currCount++;
				System.out.println("con 2");
			}else if (currCount > maxCount){
				maxCount = currCount;
				countNumber = 1;
				System.out.println("con 3");
			}else if(currCount == maxCount){
				countNumber++;	
				System.out.println("con 4");
			}
		}*/
		return;
	}

	public void check(){
		count( 1, 0);
		//count( 0, 1);


		//for( int j = 0; j < m; j++) count(0, j, 0, 1, m);

		System.out.println(maxCount + " " + countNumber);
	}

	public String toString(){
		String ans = ""; 
	      for (int i=0; i<n; i++) {
	         for (int j=0; j<m; j++)
	            ans += data[i][j];
	         ans += "\n";
	      }
	      return ans;
	  }
}