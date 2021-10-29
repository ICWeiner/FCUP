public class ED232{
	public static void main(String[] args){
		SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
		SinglyLinkedList<Integer> toRemoveList = new SinglyLinkedList<>();

		list.addFirst(2);
		list.addFirst(5);
		list.addFirst(1);
		list.addFirst(1);
		list.addFirst(2);
		list.addFirst(1);

		toRemoveList.addFirst(2);
		toRemoveList.addFirst(5);

		list.remove(toRemoveList);



		System.out.println(list);


	}
}