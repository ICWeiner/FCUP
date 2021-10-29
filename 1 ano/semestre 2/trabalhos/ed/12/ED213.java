import java.util.Scanner;

public class ED213{
	static int best = 0;
	static String bestPath = "";

	public static String maxSum(BTree<Integer> t){
		best = 0; bestPath = "";
		maxSum(t.getRoot(),0,"");
		return bestPath;
	}

	private static void maxSum(BTNode<Integer> n,int sum,String path){
		if (n == null) return;
		sum += n.getValue();
		if (n.getLeft() == null && n.getRight() == null)
			if( sum > best){
				best = sum;
				bestPath = path;
			} 
		maxSum(n.getLeft() ,sum, path + "E");
		maxSum(n.getRight() ,sum, path + "D");
	}

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();

		for(int i = 0; i < n; i++){
			BTree<Integer> t = LibBTree.readIntTree(in);

			System.out.println(maxSum(t));
			//in.nextLine();
		}
	}
}