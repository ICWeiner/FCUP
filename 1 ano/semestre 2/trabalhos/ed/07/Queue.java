public class Queue<T> implements MyQueue<T>{
	private SinglyLinkedList<T> list;

	Queue(){
		list = new SinglyLinkedList<T>();
	}

	public void enqueue(T value){
		list.addLast(value);
	}

	public T dequeue(){
		T value = list.getFirst();
		list.removeFirst();

		return value;
	}

	public T first(){
		return list.getFirst();
	}

	public int size(){
		return list.size();
	}

	public boolean isEmpty(){
		return list.isEmpty();
	}

	public String toString(){
		return list.toString();
	}
}