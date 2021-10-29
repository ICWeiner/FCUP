import java.util.Scanner;

class TestCardinais{
	public static void main(String[] args){
		Scanner stdin = new Scanner(System.in);

		int n = stdin.nextInt();
		int m = stdin.nextInt();

		Cardinais c = new Cardinais(n,m);

		c.read(stdin);

		System.out.println(c);

		c.check();
	}
}