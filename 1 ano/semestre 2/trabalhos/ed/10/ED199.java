import java.util.Scanner;

public class ED199{
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int n=in.nextInt(), b=in.nextInt() -1 ,k=in.nextInt(),counter = 0,rightEdge, leftEdge;

		String arcas = in.next();
		boolean checked[] = new boolean[n];

		if (arcas.charAt(b) == 'T'){
			counter++;
			checked[b] = true;
		}
		rightEdge = leftEdge = b;

		for(int i = 0; i < k ; i++){
			char direcao = in.next().charAt(0);
			int  movimento = in.nextInt(), m,start ,end;

			if( direcao == 'D'){
				b+= movimento;
				end = b;
				if(b > rightEdge) start = rightEdge;	
				else start = b;

				rightEdge = b;
			} 
			else{
				b-= movimento;
				start = b;
				if(b < leftEdge) end = leftEdge;	
				else end = b;

				leftEdge = b;
			} 
			for(int j = start; j <= end ; j++){
				//System.out.print(j + " ");
				if(!checked[j]){
					if (arcas.charAt(j) == 'T'){
						counter++;
						checked[j] = true;
					}
				}
			}//System.out.println();
			//System.out.println(start + " " + end );
		}
		System.out.println(counter);

		//for(int i = 0; i < n ; i++)System.out.println(checked[i]);
	}
}