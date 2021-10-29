import java.util.Scanner;

class Cliente{
	public String nome;
	public int chegada;
	public int produtos;
	public int saida;

	Cliente(String nome, int chegada, int produtos){
		this.nome = nome;
		this.chegada = chegada;
		this.produtos = produtos;
		saida = -1;
	}

	@Override
	public String toString(){
		String ans = nome + " " + chegada + " " + saida;
		return ans;
	}
}

class Caixa{
	public int processamento;
	public int clientes;
	public int produtos;
	public MyDeque<Cliente> fila;

	Caixa(int processamento){
		this.processamento = processamento;
		clientes = 0;
		produtos = 0;
		fila = new LinkedListDeque<>();
	}

	public void atenderCliente(int tempo){
		Cliente cliente = fila.first();

		cliente.saida = (cliente.produtos * processamento) + 10 + tempo;  
	}

	@Override
	public String toString(){
		String ans = clientes + " " + produtos;
		return ans;
	}

}

public class ED115{
	private static Caixa caixas[];
	private static MyDeque<Cliente> fila;


	private static int flag;
	private static int numCaixas;
	private static int numClientes;

	private static void inicializar(){
		caixas = new Caixa[numCaixas];
		fila = new LinkedListDeque<>();
	}

	private static void lerCaixas(Scanner in){
		for(int i = 0 ; i < numCaixas; i++) caixas[i] = new Caixa(in.nextInt());
	}

	private static void lerClientes(Scanner in){
		for (int i = 0; i < numClientes ;i++) fila.addLast(new Cliente(in.next(),in.nextInt(),in.nextInt()));
	}

	private static void imprimirClientes(){
		for (int i = 0; i < numClientes ;i++ ) {

			System.out.println(fila.removeFirst());
		}
	}

	private static void simular(){
		for(int i = 0,tempo = 0; i < numClientes;tempo++){
			int caixaEscolhida   = -1;
			int numClientesCaixa = 999;

			if(fila.first().chegada == tempo){
				for (int j = 0; j < numCaixas ;j++){
					if (caixas[j].fila.size() < numClientesCaixa){
						caixaEscolhida = j;
						numClientesCaixa = caixas[j].fila.size();
					}else if(caixas[j].fila.size() == numClientesCaixa && caixas[caixaEscolhida].fila.last().produtos > caixas[j].fila.last().produtos){
						caixaEscolhida = j;
						numClientesCaixa = caixas[j].fila.size();
					}
					if (!caixas[j].fila.isEmpty()){
						System.out.println(tempo);
						if (caixas[j].fila.first().saida == tempo){
							fila.addLast(caixas[j].fila.removeFirst());
							}
					}
				}
				caixas[caixaEscolhida].fila.addLast(fila.removeFirst());
				if(caixas[caixaEscolhida].fila.first().saida == -1) caixas[caixaEscolhida].atenderCliente(tempo);
				//System.out.println(caixas[caixaEscolhida].fila.first().saida);
				i++;

			}
		}
	}

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);

		flag = in.nextInt();
		numCaixas = in.nextInt();

		inicializar();

		lerCaixas(in);

		numClientes = in.nextInt();

		lerClientes(in);

		simular();

		imprimirClientes();

	}
}