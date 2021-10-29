// -----------------------------------------------------------
// Estruturas de Dados 2020/2021 (CC1007) - DCC/FCUP
// http://www.dcc.fc.up.pt/~pribeiro/aulas/edados2021/
// -----------------------------------------------------------
// Lista ligada simples
// Ultima alteracao: 03/04/2018
// -----------------------------------------------------------

public class SinglyLinkedList<T> {
   private Node<T> first;    // Primeiro no da lista
   private int size;         // Tamanho da lista

   // Construtor (cria lista vazia)
   SinglyLinkedList() {
      first = null;
      size = 0;
   }

   // Retorna o tamanho da lista
   public int size() {
      return size;
   }

   // Devolve true se a lista estiver vazia ou falso caso contrario
   public boolean isEmpty() {
      return (size == 0);
   }
   
   // Adiciona v ao inicio da lista
   public void addFirst(T v) {
      Node<T> newNode = new Node<T>(v, first); 
      first = newNode;
      size++;
   }

   // Adiciona v ao final da lista
   public void addLast(T v) {
      Node<T> newNode = new Node<T>(v, null); 
      if (isEmpty()) {
         first = newNode;
      } else {
         Node<T> cur = first;
         while (cur.getNext() != null)
            cur = cur.getNext();
         cur.setNext(newNode);         
      }
      size++;
   }

   // Retorna o primeiro valor da lista (ou null se a lista for vazia)
   public T getFirst() {
      if (isEmpty()) return null;
      return first.getValue();
   }

   // Retorna o ultimo valor da lista (ou null se a lista for vazia)
   public T getLast() {
      if (isEmpty()) return null;
      Node<T> cur = first;
      while (cur.getNext() != null)
         cur = cur.getNext();
      return cur.getValue();      
   }

   // Remove o primeiro elemento da lista (se for vazia nao faz nada)
   public void removeFirst() {
      if (isEmpty()) return;
      first = first.getNext();
      size--;
   }

   // Remove o ultimo elemento da lista (se for vazia nao faz nada)
   public void removeLast() {
      if (isEmpty()) return;
      if (size == 1) {
         first = null;
      } else {
         // Ciclo com for e uso de de size para mostrar alternativa ao while
         Node<T> cur = first;
         for (int i=0; i<size-2; i++)
            cur = cur.getNext();
         cur.setNext(cur.getNext().getNext());
      }
      size--;
   }

   public SinglyLinkedList<T> reverse(){
      SinglyLinkedList<T> reversedList = new SinglyLinkedList<>();
      Node<T> curr = first;
      
      for(int i = 0; i < size; i++){
         reversedList.addFirst(curr.getValue());
         curr = curr.getNext();
      }

      return reversedList;
   }

    public int[] occurrences (T elem){
      int tempArray[] = new int[size], array[], arraySize = 0;
      Node<T> curr = first;

      
      for(int i = 0; i < size; i++){
         if( curr.getValue().equals(elem)){
            tempArray[arraySize] = i;
            arraySize++;
         }curr = curr.getNext();
      }

      array = new int[arraySize];
      for(int i = 0; i < arraySize; i++) array[i] = tempArray[i];

      if(arraySize == 0) return null;
      return array;
   }

   public void remove(SinglyLinkedList<T> toRemove){
      int toRemoveSize = toRemove.size; 

      for(int i = 0; i < toRemoveSize ; i++){
         T elem = toRemove.first.getValue();
         this.removeAll(elem);
         toRemove.removeFirst();
      }
   }

   public void removeAll(T value){
      while (!isEmpty() && first.getValue().equals(value)) {
         removeFirst();
      }

      Node<T> cur = first;
      
      while (cur != null && cur.getNext() != null){
         if(cur.getNext().getValue().equals(value)){
            cur.setNext(cur.getNext().getNext());
            size--;
         }else{
            cur = cur.getNext();
         }
         
      }
   }
   
   // Converte a lista para uma String
   public String toString() {
      String str = "{";      
      Node<T> cur = first;
      while (cur != null) {
         str += cur.getValue();
         cur = cur.getNext();
         if (cur != null) str += ",";                     
      }      
      str += "}";
      return str;
   }
}