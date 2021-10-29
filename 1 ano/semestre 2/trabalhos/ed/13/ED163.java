import java.util.Scanner;
import java.util.Arrays;

// Classe para representar uma equipa
class Team implements Comparable<Team> {
   String name;
   int points;
   int goals;

   // Construtor
   Team(String n, int p, int g) {
      name = n;
      points = p;
      goals = g;
   }

   // Método para dar representação em String de um objecto da classe
   public String toString() {
      return  name + " " + points + " " + goals ;
   }

   public int compareTo(Team t){
      if( points - t.points != 0) return t.points - points;
      if( goals - t.goals !=0) return t.goals - goals ;
      return name.compareTo(t.name);
   }
}
public class ED163{
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		Team[] v = new Team[n];

		for(int i = 0; i < n ; i++){
			String nome = in.next();
			int pontos = in.nextInt() * 3 + in.nextInt();
			in.nextInt();

			int golos = in.nextInt() - in.nextInt();

			v[i] = new Team(nome,pontos,golos);
		}

		Arrays.sort(v);
		
		for(int i = 0; i < n ; i++) System.out.println(v[i]);
	}
}