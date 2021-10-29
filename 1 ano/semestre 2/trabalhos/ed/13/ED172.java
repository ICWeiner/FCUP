import java.util.Scanner;
import java.util.LinkedList;

public class ED172{
	public static void main(String[] args){
		BSTMap<String,Integer> map = new BSTMap<String,Integer>();
		Scanner in = new Scanner(System.in);

		while(in.hasNext()){
			String word = in.next();
			Integer value = map.get(word);

			if( value == null) map.put(word, 1);
			else map.put(word, value + 1);  
		}

		LinkedList<String> list = map.keys();

		for(int i = 0; i < list.size(); i++){
			String key = list.get(i);
			System.out.println(key + ": " + map.get(key));
		} 
	}
}