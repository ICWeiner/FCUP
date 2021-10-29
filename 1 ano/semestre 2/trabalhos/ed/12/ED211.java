import java.util.Scanner;
public class ED211{

   // --------------------------------------------------------

   // contar numeros pares
   public static int countEven(BTree<Integer> t){
      if (t.getRoot() == null) return 0;
      return countEven(t.getRoot());
   }

   private static int countEven(BTNode<Integer> n){
      if( n == null) return 0;
      else if (n.getValue() % 2 != 0) return countEven(n.getLeft()) + countEven(n.getRight());
      return 1 + countEven(n.getLeft()) + countEven(n.getRight());
   }

   public static void main(String[] args) {
      // Ler arvore de inteiros em preorder
      Scanner in = new Scanner(System.in);
      BTree<Integer> t = LibBTree.readIntTree(in);

      System.out.println(countEven(t));
   }
}