public class ED195{
	public static boolean balanced(String s){
		MyStack<Character> stack = new LinkedListStack<>();
		//int counter = 0;

		if( s.length()%2 != 0) return false;

		for(int i = 0; i < s.length(); i++){
			if(s.charAt(i) == '('  || s.charAt(i) == '[') stack.push(s.charAt(i));
			else if(!stack.isEmpty()){
				if(s.charAt(i)  == ')'  && stack.top()  ==  '(' || s.charAt(i)  == ']'  && stack.top() == '[') stack.pop();
				else return false;
			}
		}

		if(stack.isEmpty()) return true;
		else return false;
	}
}