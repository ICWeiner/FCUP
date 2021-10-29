import java.util.Scanner;

public class Unique{
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		IntSet s1 = new BooleanArrayIntSet(1000000);

		while(in.hasNext()){
			s1.add(in.nextInt());
		}
		System.out.println(s1.size());
	}
}