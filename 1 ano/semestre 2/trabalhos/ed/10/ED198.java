import java.util.Scanner;

class ED198{
    private static int max(int x,int y){
    	if(x >= y) return x;
    	return y;
    }

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);

		int n = in.nextInt();
		int v[] = new int[n];

		for(int i = 0; i < n; i++){
			v[i] = in.nextInt();
		}

		int best[] = new int[n];
		int maxSoFar = v[0];
		best[0] = v[0];

		for (int i=0; i < n -1; i++){
			int max;
			if( best[i] >= 0 ) best[i +1] = best[i] + v[i+1];
			else if( best[i]  < 0) best[i + 1] = v[i+1];
			max = max(best[i],best[i+1]);
			if( maxSoFar < max) maxSoFar = max;
		}
		System.out.println(maxSoFar);
	}
}