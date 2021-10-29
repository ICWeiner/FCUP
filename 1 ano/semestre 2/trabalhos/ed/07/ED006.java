import java.util.Scanner;

public class ED006{
	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		int gameCount;

		gameCount = in.nextInt();
		in.nextLine();

		for (int i = 0; i < gameCount  ;i++){
			CircularLinkedList<String> list = new CircularLinkedList<String>();
			int playerCount, wordCount;
			String line = in.nextLine();

			wordCount = line.split(" ").length;
			playerCount = in.nextInt();

			for (int j = 0;j < playerCount;j++ ) {
				String playerName = in.next();
				list.addLast(playerName);
			}
			if(in.hasNext()) in.nextLine();

			for(int j = 0; j < playerCount - 1;j++){
				for(int k = 0 ; k < wordCount; k++){
					list.rotate();
				}
				list.removeLast();
			}

			if(list.getFirst().equals("Carlos")) System.out.println("O Carlos nao se livrou");
			else System.out.println("O Carlos livrou-se (coitado do " + list.getFirst() +"!)");
		}
	}
}	