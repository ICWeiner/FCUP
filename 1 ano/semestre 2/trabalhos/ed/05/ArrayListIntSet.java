// -----------------------------------------------------------
// Estruturas de Dados 2020/2021 (CC1007) - DCC/FCUP
// http://www.dcc.fc.up.pt/~pribeiro/aulas/edados2021/
// -----------------------------------------------------------
// Implementa o TAD conjunto usando um array como lista de elementos
// Ultima alteracao: 10/03/2018
// -----------------------------------------------------------

public class ArrayListIntSet implements IntSet {
   private int size;   // Numero de elementos do conjunto
   private int elem[]; // Array que contem os elementos em si 
   
   ArrayListIntSet(int maxSize) {
      elem = new int[maxSize];
      size = 0;
   }

   public boolean add(int x) {
      if (!contains(x)) {
         if (size == elem.length)
            throw new RuntimeException("Maximum size of set reached");         
         elem[size] =  x;
         size++;
         return true;
      }
      return false;
   }

   public boolean remove(int x) {
      if (contains(x)) {
         int pos = 0;
         while (elem[pos] != x) pos++;
         size--;
         elem[pos] = elem[size]; // Trocar ultimo elemento 
         return true;            // com o que se removeu
      }
      return false;
   }
   
   
   public boolean contains(int x) {
      for (int i=0; i<size; i++)
         if (elem[i] == x)
            return true;
      return false;
   }

   public boolean equals(IntSet s){
      if(size != s.size()) return false;

      for(int i = 0; i < size; i++){
         if(!(s.contains(elem[i]))) return false;
      }
      return true;
   }

   public IntSet intersection(IntSet s){
      IntSet outSet = new ArrayListIntSet(size);

      for(int i = 0; i < size; i++){
         if(s.contains(elem[i])) outSet.add(elem[i]);
      }

      return outSet;
   }
   
   public void clear() {
      size = 0;
   }
   
   public int size() {
      return size;
   }

   @Override 
   public String toString() {
      String res = "{";
      for (int i=0; i<size; i++) {
         if (i>0) res += ",";
         res += elem[i];
      }
      res += "}";
      return res;
   }
}