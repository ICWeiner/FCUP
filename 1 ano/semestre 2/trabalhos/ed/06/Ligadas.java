// -----------------------------------------------------------
// Estruturas de Dados 2020/2021 (CC1007) - DCC/FCUP
// http://www.dcc.fc.up.pt/~pribeiro/aulas/edados2021/
// -----------------------------------------------------------
// Exemplo de utilizacao da lista ligada simples
// Ultima alteracao: 01/04/2018
// -----------------------------------------------------------

public class Ligadas {
   public static void main(String[] args) {

		// Criacao de lista de inteiros
		/*SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();

		// Escrevendo lista (no inicio esta vazia)
		System.out.println(list);

		// Verificando se esta vazia
		System.out.println("isEmpty? " + list.isEmpty());     

		// Adicionando numeros de 1 a 5 ao final da lista
		for (int i=1; i<=5; i++)
		 list.addLast(i);
		System.out.println(list);

		// Adicionando numeros de 6 a 10 ao inicio da lista
		for (int i=6; i<=10; i++)
		 list.addFirst(i);
		System.out.println(list);

		// Verificando o tamanho da lista
		System.out.println("size = " + list.size());

		// Retirando o primeiro elemento
		list.removeFirst();
		System.out.println(list);

		// Retirando o ultimo elemento
		list.removeLast();
		System.out.println(list);

		// Verificando se esta vazia
		System.out.println("isEmpty? " + list.isEmpty());

		// Escreve o primeiro e ultimo elementos
		System.out.println("getFirst() = " + list.getFirst());
		System.out.println("getLast() = " + list.getLast());*/

		// Criacao de lista de letras
		SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();

		list1.addLast(2);
		list1.addLast(2);
		list1.addLast(1);

		list1.addLast(2);
		list1.addLast(2);
		list1.addLast(1);
		list1.addLast(3);
		list1.addLast(4);
		list1.addLast(2);
		list1.addLast(1);
		

		System.out.println(list1);
		System.out.println(list1.count(2));
		System.out.println(list1.size());
		/*System.out.println(list1.count(200));
		System.out.println(list1.count(9999));
		System.out.println(list1.count(1));*/

		list1.removeAll(2);
		

		System.out.println(list1);
		System.out.println(list1.count(2));
		System.out.println(list1.size());


		//System.out.println(list1.count("cc"));

		/*list1.addFirst(1);
		list1.addFirst(2);
		list1.addFirst(3);
		list1.addFirst(4);
		list1.addFirst(2);
		System.out.println(list1);
		
		System.out.println(list1.count(2));

		list1.addLast(2);
		System.out.println(list1);

		System.out.println(list1.count(2));*/


		/*// Criacao de lista de pairs
		SinglyLinkedList<Pair> list2 = new SinglyLinkedList<>();
		SinglyLinkedList<Pair> list2Copy = new SinglyLinkedList<>();
		Pair p1,p2,p3,p4;

		p1 = new Pair(1,2);
		p2 = new Pair(2,3);
		p3 = new Pair(3,4);
		p4 = new Pair(4,5);

		list2.addFirst(p3);
		list2.addFirst(p2);
		list2.addFirst(p1);

		System.out.println(list2);
		list2Copy = list2.copy();

		//list2Copy.addLast(p4);

		System.out.println(list2.remove(2));

		System.out.println(list2.remove(1));

		System.out.println(list2);
		System.out.println(list2Copy);
		System.out.println(list2Copy.size());

		System.out.println(list2.get(1));


		list2Copy.duplicate();
		System.out.println(list2Copy);
		System.out.println(list2Copy.size());*/


   }
}