// -----------------------------------------------------------
// Estruturas de Dados 2020/2021 (CC1007) - DCC/FCUP
// http://www.dcc.fc.up.pt/~pribeiro/aulas/edados2021/
// -----------------------------------------------------------
// Arvore binaria "normal"
// Ultima alteracao: 26/04/2018
// -----------------------------------------------------------

public class BTree<T> {   
   private BTNode<T> root; // raiz da arvore

   // Construtor
   BTree() {
      root = null;
   }

   // Getter e Setter para a raiz
   public BTNode<T> getRoot() {return root;}
   public void setRoot(BTNode<T> r) {root = r;}

   // Verificar se arvore esta vazia
   public boolean isEmpty() {
      return root == null;
   }

   // --------------------------------------------------------

   // Numero de nos da arvore   
   public int numberNodes() {
      return numberNodes(root);
   }

   private int numberNodes(BTNode<T> n) {
      if (n == null) return 0;
      return 1 + numberNodes(n.getLeft()) + numberNodes(n.getRight());
   }

   // --------------------------------------------------------

   // Altura da arvore
   public int depth() {
      return depth(root);
   }

   private int depth(BTNode<T> n) {
      if (n == null) return -1;
      return 1 + Math.max(depth(n.getLeft()), depth(n.getRight()));
   }

   // --------------------------------------------------------

   // Numero de folhas
   public int numberLeafs(){
      return numberLeafs(root);
   }

   private int numberLeafs(BTNode<T> n){
      if(n == null) return 0;
      if( n.getRight() == null && n.getLeft() == null) return 1;
      return 0 + numberLeafs(n.getRight()) + numberLeafs(n.getLeft());
   }

   // --------------------------------------------------------

   //contar nos internos
   public int internal(){
      return internal(root);
   }

   private int internal(BTNode<T> n){
      if (n == null) return 0;
      if (n.getLeft() == null && n.getRight() == null) return 0;
      return 1 + internal(n.getLeft()) + internal(n.getRight());
   }

   // --------------------------------------------------------

   //cortar nos em d altura
   public void cut(int d){
      if(d <= 0) root = null;
      cut(root,d - 1);
   }

   private void cut(BTNode<T> n,int d){
      if (n == null) return;
      if (d == 0){ n.setLeft(null); n.setRight(null); return;}
      cut(n.getLeft(),d -1);
      cut(n.getRight(),d -1);
   }

   // --------------------------------------------------------

   //contar maior numero de nos e quantas vezes esse numero se repete
   public int[] maxLevel(){
      int[] max = new int[2]; max[0] = -1;max[1] = 1;
      int depth = depth() + 1;
      for(int i = 0; i < depth; i++){
         int tmp = nodesLevel(i);
         
         if(tmp > max[0]){
            max[0] = tmp;
            max[1] = 1;
         }else if( tmp == max[0]) max[1]++;
      }
      return max;
   }


   // --------------------------------------------------------

   //contar filhos unicos
   public int count(){
      return count(root);
   }

   private int count(BTNode<T> n){
      if (n == null) return 0;
      if (n.getLeft() == null && n.getRight() != null) return 1 + count(n.getRight());
      if (n.getLeft() != null && n.getRight() == null) return 1 + count(n.getLeft());
      //System.out.println(n.getLeft() + " " + n.getRight());
      return count(n.getLeft()) + count(n.getRight());
   }

   // --------------------------------------------------------

   //altura mais baixo de um valor
   public int level(T v){
      if(!contains(v)) return -1;
      return level(root,v,0);
   }

   public int level(BTNode<T> n,T v,int h){
      if (n == null) return 102;
      if (n.getValue().equals(v)) return h;
      int tmp = level(n.getLeft(),v,h+1),tmp2 = level(n.getRight(),v,h+1);
      if (tmp != 102 && tmp < tmp2) return tmp;
      return tmp2;

      //return  level(n.getLeft(),v,h+1) + level(n.getRight(),v,h+1);
   }

   // --------------------------------------------------------

   // arvore estritamente binaria
   public boolean strict(){
      return strict(root);
   }

   private boolean strict(BTNode<T> n){
      if(n == null) return true;
      if(n.getRight() == null && n.getLeft() == null) return true;
      if(n.getRight() == null && n.getLeft() != null) return false;
      if(n.getRight() != null && n.getLeft() == null) return false;
     /* if( (n.getRight() == null ||  n.getRight().getValue() > n.getValue() && n.getLeft() != null ) &&
        (  n.getLeft() == null  ||  n.getLeft().getValue() > n.getValue() && n.getRight() != null ) ) return true;*/
      return  strict(n.getRight()) && strict(n.getLeft());
   }

   // --------------------------------------------------------

   // caminho
   public T path(String s){
      if(s == "R") return root.getValue();
      else return path(root, s);
   }

   private T path(BTNode<T> n,String s){
      if (s.length() == 0) return n.getValue();
      else if(s.charAt(0) == 'E') return path(n.getLeft()  ,s = s.substring(1));
      else if(s.charAt(0) == 'D') return path(n.getRight() ,s = s.substring(1));
      return n.getValue();
   }

   // --------------------------------------------------------

   // nos numa dada profundidade
   public int nodesLevel(int k){
      if(k == 0) return 1;
      else return nodesLevel(root, k);
   }

   private int nodesLevel(BTNode n, int k){
      //System.out.println(k);
      if (n == null) return 0;
      else if (k == 0) return 1;
      else return nodesLevel(n.getLeft() , k -1) + nodesLevel(n.getRight() , k -1);
   }

   // --------------------------------------------------------
   
   // O elemento value esta contido na arvore?
   public boolean contains(T value) {
      return contains(root, value);
   }

   private boolean contains(BTNode<T> n, T value) {
      if (n==null) return false;
      if (n.getValue().equals(value)) return true;
      return contains(n.getLeft(), value) || contains(n.getRight(), value);
   }

   // --------------------------------------------------------

   // Imprimir arvore em PreOrder
   public void printPreOrder() {
      System.out.print("PreOrder:");
      printPreOrder(root);
      System.out.println();
   }

   private void printPreOrder(BTNode<T> n) {
      if (n==null) return;
      System.out.print(" " + n.getValue() );
      printPreOrder(n.getLeft());
      printPreOrder(n.getRight());
   }

   // --------------------------------------------------------
   
   // Imprimir arvore em InOrder
   public void printInOrder() {
      System.out.print("InOrder:");
      printInOrder(root);
      System.out.println();
   }

   private void printInOrder(BTNode<T> n) {
      if (n==null) return;
      printInOrder(n.getLeft());
      System.out.print(" " + n.getValue());
      printInOrder(n.getRight());
   }

   // --------------------------------------------------------

   // Imprimir arvore em PostOrder
   public void printPostOrder() {
      System.out.print("PostOrder:");
      printPostOrder(root);
      System.out.println();
   }

   private void printPostOrder(BTNode<T> n) {
      if (n==null) return;
      printPostOrder(n.getLeft());
      printPostOrder(n.getRight());
      System.out.print(" " + n.getValue());
   }

   // --------------------------------------------------------

   // Imprimir arvore numa visita em largura (usando TAD Fila)
   public void printBFS() {
      System.out.print("BFS:");
      
      MyQueue<BTNode<T>> q = new LinkedListQueue<BTNode<T>>();
      q.enqueue(root);
      while (!q.isEmpty()) {
         BTNode<T> cur = q.dequeue();
         if (cur != null) {
            System.out.print(" " + cur.getValue());
            q.enqueue(cur.getLeft());
            q.enqueue(cur.getRight());
         }
      }
      System.out.println();
   }

   // --------------------------------------------------------
   
   // Imprimir arvore numa visita em profundidade (usando TAD Pilha)
   public void printDFS() {
      System.out.print("DFS:");
      
      MyStack<BTNode<T>> q = new LinkedListStack<BTNode<T>>();
      q.push(root);
      while (!q.isEmpty()) {
         BTNode<T> cur = q.pop();
         if (cur != null) {
            System.out.print(" " + cur.getValue());
            q.push(cur.getLeft());
            q.push(cur.getRight());
         }
      }
      System.out.println();
   }

}
