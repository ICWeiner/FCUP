public class ED197{
	public static MyQueue<Integer> merge(MyQueue<Integer> a, MyQueue<Integer> b){
		MyQueue<Integer> q = new LinkedListQueue<>();

		int size = a.size() + b.size();

		for(int i = 0; i < size; i++){
			if(!a.isEmpty() && !b.isEmpty()){
				if(( a.first() > b.first())) q.enqueue(b.dequeue());
				else q.enqueue(a.dequeue());
			}else if(a.isEmpty()) q.enqueue(b.dequeue());
			else q.enqueue(a.dequeue());
		}
		return q;
	}
}