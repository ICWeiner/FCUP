import java.util.Scanner;

public class ED007{
	public static void main(String args[]){
		Scanner in = new Scanner(System.in);

		int n = in.nextInt();

		in.nextLine();

		for(int i = 0; i < n ; i++){
			MyStack<Character> s = new LinkedListStack<>();
			String line = in.nextLine();
			int posCounter = 0;
			int numberCounter = 0;
			int symbolCounter = 0;
			int error = -1;
			boolean correct = true;


			for(int j = 0; j < line.length(); j+=2){

				//System.out.println();

				if( Character.isDigit( line.charAt(j) ) ) numberCounter++;//s.push(lineScanner.nextInt());
				else if( line.charAt(j) == '+' || line.charAt(j) == '-' || line.charAt(j) == '*' || line.charAt(j) == '/') symbolCounter++;
				else if( line.charAt(j) == '(' || line.charAt(j) == '[') s.push(line.charAt(j));
				else if( !s.isEmpty() && line.charAt(j) == ')' && s.top() == '(') s.pop();
				else if( !s.isEmpty() && line.charAt(j) == ']' && s.top() == '[') s.pop();
				else{
					correct = false;
					error = j;
				}
			}

			if(correct && s.isEmpty()) System.out.println("Expressao bem formada");
			else if(error!= -1) System.out.println("Erro na posicao " + error);
			else System.out.println("Ficam parenteses por fechar"); 
		}
	}
}