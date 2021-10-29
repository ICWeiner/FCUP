import java.util.Scanner;

public class Game{
	private int n;
	private char data[][];

	public static void main (String[] args){
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		Game g = new Game(n);
		g.read(in);
		g.check();
	}

	Game(int n){
		this.n = n;
		data = new char[n][n];
	}

	public void read(Scanner in){
		for (int i = 0; i < n;i++){
			String buf = in.next();
			for (int j = 0;j < n ; j++) {
				data[i][j] = buf.charAt(j);
			}
		}
	}

	public void verify(int x, int y, int incx, int incy){
		if(data[y][x] == '.') return;
		for (int i = 0 , yy = y , xx = x; i < n; i++, xx += incx, yy += incy)
			if (data[y][x] != data[yy][xx]) return;
		win(data[y][x]);
	}

	void check(){
		for (int i = 0; i < n; i++) verify(i,0,1,0);
		for (int j = 0; j < n; j++) verify(0,j,0,1);
		verify(0,0,1,1);
		verify(0,n-1,1,-1);

		if (!finished()) System.out.println("Jogo incompleto");
		else 			 System.out.println("Empate");
	}

	boolean finished(){
		for (int i = 0; i < n;i++){
			for (int j = 0;j < n ; ) {
				if (data[i][j] == '.') return false;
			}
		}
		return true;
	}

	void win(char player){
		System.out.println("Ganhou o " + player);
		System.exit(0);
	}
} 