import java.util.Scanner;

public class ReadNumbers{

	static int calcSum(int v[]){
		int sum=0;

		for(int i = 0; i < v.length; i++){
			sum+=v[i];
		}

		return sum;
	}

	static int amplitude(int v[]){
		int max,min;

		max=min=v[0];
		for(int i = 1; i < v.length; i++){
			if( v[i] > max)
				max = v[i];
			else if(v[i] < min)
				min = v[i];
		}

		return (max-min);
	}

	public static void main(String[] args){
		int amplitude,sum;
		float media;

		Scanner stdin = new Scanner(System.in);

		int n = stdin.nextInt();

		int v[] = new int[n];

		for(int i = 0; i <n; i++){
			v[i] = stdin.nextInt();
		}

		sum=calcSum(v);

		amplitude=amplitude(v);

		media = (float) sum/n;

		System.out.printf("%.2f\n", media);
		System.out.println(amplitude);
	}
}