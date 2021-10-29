import java.util.Scanner;

class TestFraction{
	public static void main(String[] args) {
		Fraction fraction1 = new Fraction(1,4);
		Fraction fraction2 = new Fraction(2,5);
		Fraction fraction3 = fraction1.add(fraction2);
		Fraction fraction4 = fraction1.multiply(fraction2);
		//Scanner stdin = new Scanner(System.in);

		System.out.println(fraction1);

		System.out.println(fraction3);


		System.out.println(fraction4);

		fraction4.simplify();

		System.out.println(fraction4);

	}
}