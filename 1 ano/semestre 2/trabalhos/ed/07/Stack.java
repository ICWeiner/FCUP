public class Stack<T> implements MyStack<T>{
	private SinglyLinkedList<T> list;

	Stack(){
		list = new SinglyLinkedList<T>();
	}

	public void push(T value){
		list.addLast(value);
	}

	public T pop(){
		T value = list.getLast();
		list.removeLast();

		return value;
	}

	public T top(){
		T value = list.getLast();

		return value;
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