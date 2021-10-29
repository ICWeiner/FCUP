import java.util.Scanner;

public class ED165{
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		BSTree<Integer> t = new BSTree<>();
		int elems[] = new int[n];

		for(int i = 0; i < n; i++) elems[i] = in.nextInt();

		for(int i = 0; i < n; i++)
			for(int j = 0; j < n; j++) t.insert(elems[i] + elems[j]);
		
		int p = in.nextInt();
		int nums[] = new int[p];

		for(int i = 0; i < p; i++) nums[i] = in.nextInt();

		for(int i = 0; i < p; i++){
			System.out.print(nums[i] + ": ");
			if (t.contains(nums[i])) System.out.print("sim");
			else System.out.print("nao");
			System.out.println();
		}
	}
}