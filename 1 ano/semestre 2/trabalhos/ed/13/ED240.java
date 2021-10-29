import java.util.Scanner;
import java.util.LinkedList;
import java.util.Arrays;

public class ED240{
	private static int min(BTNode<Integer> n){
		if (n == null) return Integer.MAX_VALUE;
		return Math.min(Math.min(min(n.getRight()),min(n.getLeft())),n.getValue());
	}

	public static String[] paths(BTree<Integer> t){
		int min = min(t.getRoot());
		LinkedList<String> list = new LinkedList<>();
		paths(t.getRoot(),list,"",min);

		int i = 0;
		String[] ans = new String[list.size()];
		for( String s : list) ans[i++] = s;

		return ans;
	}

	private static void paths(BTNode<Integer> n, LinkedList<String> list,String path,int min){
		if (n == null) return;
		if (n.getValue() == min){
			if(path.equals("")) list.addLast("R");
			else list.addLast(path);
		}
		paths(n.getLeft(),list,path + "E", min);
		paths(n.getRight(),list,path + "D", min);
	}

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		BTree<Integer> t = LibBTree.readIntTree(in);

		System.out.println(Arrays.toString(paths(t)));
	}
}