import java.util.Scanner;
public class ED212{
   // soma de todos os niveis
   public static int[] sumLevels(BTree<Integer> t) {
      int nums[] = new int[t.depth() + 1];
      sumLevels(t.getRoot(),0,nums);
      return nums;
   }

   private static void sumLevels(BTNode<Integer> n,int pos,int[] nums) {
      if( n == null) return;
      nums[pos] += n.getValue();
      sumLevels(n.getLeft(),pos + 1, nums);
      sumLevels(n.getRight(),pos + 1, nums);
   }

   public static void main(String[] args) {
      // Ler arvore de inteiros em preorder
      Scanner in = new Scanner(System.in);
      BTree<Integer> t = LibBTree.readIntTree(in);
      int n[] = sumLevels(t);

      //System.out.println(depth(t) + 1);
      
      for(int i = 0 ; i < n.length;i++) System.out.println(n[i]);
      
   }
}