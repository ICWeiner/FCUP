import java.util.Scanner;

class Cardinais{
	final char CARDINAL = '#';
	int n;
	int m;
	int maxCount;
	int countNumber;
	char[][] data;

	Cardinais(int n, int m){
		this.n = n;
		this.m = m;
		data = new char[n][m];
		maxCount = -1;
		countNumber = 1;
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

	public void check(){
		for(int i = 0; i < n; i++){
			for( int j = 0; j < m; j++){
				int countVertical = count(i,j,0,1);
				int countHorizontal = count(i,j,1,0);
				checkMaxcount(countVertical);
				checkMaxcount(countHorizontal);

			}
		}
		System.out.println(maxCount + " " + countNumber);
	}


	private int count(int x, int y,int incx, int incy){
		int count = 0;

		for( int xx = x, yy = y; xx < n && yy < m;xx+=incx, yy+=incy){
			if( data[xx][yy] != CARDINAL) return count;
			count++;
		}
		return count;
	}

	private void checkMaxcount(int count){
		if( count > maxCount){
			maxCount = count;
			countNumber = 1;
		}
		else if( count == maxCount) countNumber++;
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



class ED216{
	public static void main(String[] args){
		Scanner stdin = new Scanner(System.in);

		int n = stdin.nextInt();
		int m = stdin.nextInt();

		Cardinais c = new Cardinais(n,m);

		c.read(stdin);

		//System.out.println(c);

		c.check();
	}
}