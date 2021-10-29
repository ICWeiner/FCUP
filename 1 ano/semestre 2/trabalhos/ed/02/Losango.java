import java.util.Scanner;

public class Losango{

	public static void main(String[] args) {

		int spacingCounter,n=2;

		Scanner stdin = new Scanner(System.in);

		
		while(n%2!=1){
			n = stdin.nextInt();
		}

		spacingCounter=(n-1)/2;
		
		for(int i=1;i<n;i+=2){

		    for (int l = 0; l < spacingCounter; l++){
		        System.out.print(".");
		    }
		    
		    for(int j = 0; j < i; j++){
		        System.out.print("#");
		    }

		    for (int l = 0; l < spacingCounter; l++){
		        System.out.print(".");
		    }spacingCounter--;

		    System.out.println();
		}
		
		for(int i=n;i>=1;i-=2){

			for (int l = 0; l < spacingCounter; l++){
		        System.out.print(".");
		    }

		    for(int j = 0; j < i; j++){
		        System.out.print("#");
		    }

		    for (int l = 0; l < spacingCounter; l++){
		        System.out.print(".");
		    }spacingCounter++;

		    System.out.println();


		}
	}
}
