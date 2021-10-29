public class ED236{
	public static void main(String[] args){
		SinglyLinkedList<Character> list = new SinglyLinkedList<>();
		SinglyLinkedList<Integer> cutList = new SinglyLinkedList<>();

		list.addFirst('e');
		list.addFirst('d');
		list.addFirst('c');
		list.addFirst('b');
		list.addFirst('a');


		System.out.println(list);

		//System.out.println(cutList);

		list.shift(4);

		System.out.println(list);
	}
}