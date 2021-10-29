public class Losango
{
	public static void main(String[] args) {

		writeChar=args[0];
		int n=11;
		int spacing=(n-1)/2,limiter=1;
		
		for(int i=1;i<n;i+=2){

		    for (int l=0;l<spacing;l++){
		        System.out.print(" ");
		    }
		    spacing--;
		    for(int j=0;j<i;j++){
		        System.out.print("#");
		    }System.out.println();
		}

		spacing=(n-1)/2;
		
		for(int i=n;i>=1;i-=2){

		    for(int j=0;j<i;j++){
		        System.out.print("#");
		    }System.out.println();

			for (int l=0;l<limiter;l++){
		        System.out.print(" ");
		    }limiter++;
		}
	}
}
