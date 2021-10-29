import java.util.Scanner;

public class ED222{
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int n=in.nextInt(), k=in.nextInt() ,t=in.nextInt(),half,size= n - (k -1);

		if( k%2 == 0) half = k/2;
		else half = (k+1)/2;

		int locais[] = new int[n];
		int biggerThanT[] = new int[size];
		int counter = 0;

		for(int i = 0;i < n;i++) locais[i] = in.nextInt();

		for (int i=0; i < k; i++){
			if( locais[i] >= t) biggerThanT[0]++;
		}
		if(biggerThanT[0]>= half) counter++;
		//System.out.println(biggerThanT[0]);

		for (int i=1; i < size; i++){
			biggerThanT[i] = biggerThanT[i-1];
			if(locais[i-1] >= t ) biggerThanT[i]--;
			if(locais[i+k-1] >=t) biggerThanT[i]++; 
			//System.out.println(locais[j]);
			if( biggerThanT[i] >= half) counter++;
			//System.out.println(biggerThanT[i] + " " + (i -1) + " " + (i+k-1));
		}
		System.out.println(counter);
	}
}