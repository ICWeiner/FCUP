public class Test{
	public static void main(String[] args){
		MyQueue<Integer> q,a,b;
		
		a = new LinkedListQueue<>();
		b = new LinkedListQueue<>();
		
		a.enqueue(2);
		a.enqueue(4);
		a.enqueue(8);
		a.enqueue(10);
		b.enqueue(1);
		b.enqueue(4);
		b.enqueue(9);

		q = ED197.merge(a,b);

		//System.out.println(q);
	}
}