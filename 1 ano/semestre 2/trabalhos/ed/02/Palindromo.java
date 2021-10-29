import java.util.Scanner;

public class Palindromo{

	public static boolean isPalindrome(String sentence){

		//System.out.println(sentence);

		sentence=sentence.replaceAll("\\s+","");
		sentence=sentence.toLowerCase();

        int j=sentence.length()-1;
        for(int i=0;i<j;i++){
        	
        	while(Character.isLetter(sentence.charAt(i))==false)
        		i++;
        	while(Character.isLetter(sentence.charAt(j))==false)
        		j--;

        	//System.out.println(sentence.charAt(i) + " " + sentence.charAt(j));

            if (sentence.charAt(i) != sentence.charAt(j)){
                return false;
            }j--;
        }
        return true;
    }

	public static void main(String[] args){

		Scanner stdin = new Scanner(System.in);

		int n = stdin.nextInt();

		stdin.nextLine();

		System.out.println(n);

		for(int i = 0;i<n;i++){
			String sentence = stdin.nextLine(); 
			boolean palindrome = isPalindrome(sentence);

			if (palindrome == true)
				System.out.println("sim");
			else
				System.out.println("nao");
		}
		
	}
}