import java.util.Scanner;

public class Pizza{

	public static void main(String[] args){

		int dislikeSize,menuSize,orderable=0;
		boolean dislikeIngredient=false;

		Scanner stdin = new Scanner(System.in);

		dislikeSize = stdin.nextInt();

		int dislike[] = new int[dislikeSize] ;

		for(int i = 0; i < dislikeSize; i++){
			dislike[i] = stdin.nextInt();
		}

		menuSize = stdin.nextInt();

		for(int i = 0; i < menuSize; i++){
			int ingredientSize = stdin.nextInt();
			//System.out.println("posicao no menu:" + (1+i) + " de " + menuSize);

			for(int j = 0; j < ingredientSize; j++){
				//System.out.println("ingredientes da pizza:" + (1+j) + " de " + ingredientSize);
				int currIngredient = stdin.nextInt();

				for( int k = 0; k < dislikeSize; k++){
					//System.out.println("numero do ingrediente a ser verificado :" + (1+k) + " de " + dislikeSize);
					if(currIngredient == dislike[k]){
						dislikeIngredient = true;
						break;
					}
				}
			}if( dislikeIngredient == false){
					orderable++;
					//System.out.println("incrementado");
				}
				else
					dislikeIngredient=false;
		}

		System.out.println(orderable);
	}
}