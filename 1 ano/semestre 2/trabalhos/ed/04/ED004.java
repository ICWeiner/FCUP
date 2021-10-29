import java.util.Scanner;

public class ED004 {
	public static void main (String[] args){
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		Game g = new Game(n);
		g.read(in);
		g.check();
	}
}