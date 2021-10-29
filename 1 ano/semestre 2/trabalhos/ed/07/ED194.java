public class ED194{
	public static void reverse(MyStack<Integer> s,int n){
		int[] elems = new int[n];

		for(int i = 0 , j = n - 1 ; i < n; i ++, j--) elems[i] = s.pop();

		for(int i = 0 ; i < n;i++) s.push(elems[i]); 
	}
}