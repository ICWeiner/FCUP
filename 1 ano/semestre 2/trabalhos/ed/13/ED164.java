import java.util.Scanner;

public class ED164{
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		BSTree<String> t = new BSTree<>();

		in.nextLine();

		for(int i = 0; i < n; i++){
			t.insert(in.nextLine());
		}
		System.out.println(t.numberNodes());

	}
}