public class ED196{
	public static void process(MyQueue<String> q, MyQueue<String> a, MyQueue<String> b){
		int size = q.size();

		for(int i = 0; i < size ; i+=2){
			String name = q.dequeue();
			String letter = q.dequeue();

			if( letter.equals("A"))
				a.enqueue(name);
			else if( letter.equals("B"))
				b.enqueue(name);
			else if( letter.equals("X")){
				if(a.size() < b.size()) a.enqueue(name);
				else if( a.size() > b.size()) b.enqueue(name);
			}
		}
	}
}