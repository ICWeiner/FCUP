// -----------------------------------------------------------
// Estruturas de Dados 2020/2021 (CC1007) - DCC/FCUP
// http://www.dcc.fc.up.pt/~pribeiro/aulas/edados2021/
// -----------------------------------------------------------
// Interface que define o TAD conjunto de numeros inteiros
// Ultima alteracao: 10/03/2018
// -----------------------------------------------------------

// Interface que define o TAD conjunto de numeros inteiros
public interface IntSet {
	public boolean contains(int x); // Retorna true se x esta no conjunto
	public boolean add(int x);      // Adiciona x ao conjunto
	public boolean remove(int x);   // Remove x do conjunto
	public int     size();          // Retorna o numero de elementos do conjunto
	public void    clear();         // Limpa o conjunto (torna-o vazio)

	// Metodos a adicionar (nao existentes na classe base)
	public boolean equals(IntSet s);      // Retorna true se ambos os conjuntos sao iguais
	public IntSet intersection(IntSet s); // devolve um novo conjunto: a intersecao de ambos
}