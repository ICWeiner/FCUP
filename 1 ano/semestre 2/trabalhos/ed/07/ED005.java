import java.util.Scanner;

public class ED005{
	public static void main(String args[]){
		Scanner in = new Scanner(System.in);

		int n = in.nextInt();

		in.nextLine();

		for(int i = 0; i < n ; i++){
			MyStack<Integer> s = new LinkedListStack<>();
			String line = in.nextLine();
			Scanner lineScanner = new Scanner(line);
			boolean correct = true;

			
			while(lineScanner.hasNext()){
				if(lineScanner.hasNextInt()) s.push(lineScanner.nextInt());
				else if(s.size() > 1){
					char symbol = lineScanner.next().charAt(0);
					int a = s.pop();
					int b = s.pop();

					if( symbol == '+') s.push(a + b);
					else if( symbol == '-') s.push(b - a);
					else if( symbol == '*') s.push(a * b);
					else if( symbol == '/'){
						if (a > b && b!= 0) s.push(a / b);
						else if( a!= 0) s.push(b / a);
					} 
				}else{
					correct = false;
					break;
				}
			}
			if(correct && s.size() == 1) System.out.println(s.pop());
			else System.out.println("Expressao Incorrecta");
		}
	}
}