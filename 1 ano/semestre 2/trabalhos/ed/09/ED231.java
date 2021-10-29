import java.util.Scanner;

public class ED231{
	private static char graph[][];
	private static int values[];
	

	private static int n;
	private static int y;

	public static void readValues(Scanner in){
		n = in.nextInt();
		int flag;
		values = new int[n];

		for (int i = 0 ;i < n; i++){
			values[i] = in.nextInt();
		}
	}

	public static void printExtremes(){
		int min , max;
		min = max = values[1] - values [0];

		for (int i = 0;i < (n- 1);i++ ) {
			int diff = values[i + 1] - values[i];
			if(min > diff) min = diff;
			else if(max < diff) max = diff;
		}

		System.out.println( min + " " + max);
	}

	public static void printGrowth(){
		int periods, max,curr;
		max = curr = periods = 0;

		for(int i = 1;i < n ; i++ ){
			double today, last ,growth;
			today = values[i];
			last = values[i - 1];
			growth = (today - last )/last *100;


			if (growth <= 5) curr++;
			else if (curr > 0){
				if (curr > max) max = curr;
				curr = 0;
				periods++;
			}
		}

		if (curr > 0){//melhorar esta parte
				if (curr > max) max = curr;
				curr = 0;
				periods++;
			}

		System.out.println(periods + " " + max);
	}

	public static void calcGraph(){
		y = values[n - 1] / 100;

		graph = new char[n][y];


		for(int i = 0; i < n ; i++){
			int percentage = values[i]/100;


			for(int j = 0; j < percentage && j < y ;j++){
				graph[i][j] = '#';
			}for(int j = percentage; j < y; j++){
				graph[i][j] = '.';
			}
		}
	}

	public static void printGraph(){
		calcGraph();

		for(int i = y- 1;i >=0; i--){
			for(int j = 0;j < n; j++){
				System.out.print(graph[j][i]);
			}
			System.out.println();
		}
	}

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int flag;

		readValues(in);
		flag = in.nextInt();

		if (flag == 1){
			printExtremes();
		}else if (flag == 2){
			printGrowth();
		}else{
			printGraph();
		}
	}
}