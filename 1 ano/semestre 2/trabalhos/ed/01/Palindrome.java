public class Palindrome {
    
    public static boolean isPalindrome(String word){
        int j=word.length()-1;
        
        for(int i=0;i<j;i++){
            if (word.charAt(i) != word.charAt(j)){
                return false;
            }j--;
        }
        return true;
    }

    public static void main(String[] args) {

        String teste="HelleH";
        
        boolean value=isPalindrome(teste);
        
        System.out.println(value);
    }
} 
