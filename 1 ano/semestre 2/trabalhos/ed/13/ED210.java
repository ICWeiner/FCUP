public class ED210{
	public static void main(String[] args){
		// Criacao da Arvore
		BSTree<Integer> t = new BSTree<Integer>();

		// Inserindo 11 elementos na arvore binaria de pesquisa
		int[] data = {5,3,1,2,7,6,8};
		for (int i=0; i<data.length; i++) t.insert(data[i]);

		System.out.println(t.valid());
	}

}