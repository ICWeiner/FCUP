import java.util.Scanner;

class Bombeiro{
	String nome;
	int eventos;
	int minutos;

	Bombeiro(String nome){
		this.nome = nome;
		eventos = 0;
		minutos = 0;
	}


	@Override
	public String toString(){
		return(nome + " " + eventos  + " " + minutos);
	}
}

class Evento{
	int inicio;
	Bombeiro participantes[];

	Evento(int inicio, Bombeiro participantes[]){
		this.inicio = inicio;
		this.participantes  = participantes;
	}

	@Override
	public String toString(){
		return(inicio + " " + participantes);
	}
}

public class ED095{
	private static final String EVENTO[] = {"PARTIDA","CHEGADA","FIM"}; //arranjar outro nome ou apagar

	private static MyQueue<Bombeiro> fila;
	private static Evento eventos[];

	private static int numBombeiros;
	private static int numEventos;

	private static void simular(Scanner in){
		int k = 0;
		while(in.hasNext()){
			String tipo = in.next();

			if(tipo.equals(EVENTO[0])){
				int  numero = in.nextInt();
				int nBombeiros = in.nextInt();
				int inicio  = in.nextInt();	

				if(nBombeiros > fila.size()) nBombeiros = fila.size();

				Bombeiro participantes[] = new Bombeiro[nBombeiros];

				for(int j = 0; j < nBombeiros; j++){
					participantes[j] = fila.dequeue();
					participantes[j].eventos++;
				}
				eventos[numero] = new Evento(inicio, participantes);

			}else if( tipo.equals(EVENTO[1]) ){
				int eventoF = in.nextInt();
				int chegada = in.nextInt();
				int tempo = chegada - eventos[eventoF].inicio;
				int limit = eventos[eventoF].participantes.length;

				//System.out.println(eve)

				for (int j = 0; j < limit; j++ ) {
					eventos[eventoF].participantes[j].minutos += tempo;
					fila.enqueue(eventos[eventoF].participantes[j]);
				}
				k++;
			}else if( tipo.equals(EVENTO[2]) ) {
				numEventos = k + 1;
				break;
			}
		}
	}

	private static void mostrarNumeroEventos(Scanner in){
		int i = 0;
		while(in.hasNext()){
			if(in.next().equals(EVENTO[0])) i++;
		}
		System.out.println("Ocorreram " + i + " eventos");
	}

	private static void mostrarEventos(){
		int limit = numEventos;

		System.out.println("Bombeiros Destacados");

		int i;

		if(eventos[0] == null){//band-aid, casos teste as vezes comecam em 1 e outras em 0
			i = 1;
		} 
		else {
			i = 0;
			limit--;
		}

		for(; i < limit; i++){
			System.out.println("EVENTO " + i );
			int innerLimit = eventos[i].participantes.length;
			if(innerLimit == 0){
				System.out.println("Nenhum");
			}else{
				for(int j = 0; j < innerLimit; j++){
					System.out.println(eventos[i].participantes[j].nome);
				}
			}
		}
	}

	private static void mostrarBombeiros(){
		System.out.println("Listagem de Bombeiros");
		for(int i = 0; i < numBombeiros; i++){
			Bombeiro bombeiro = fila.dequeue();
			System.out.println(bombeiro.nome + " " + bombeiro.eventos + " " + bombeiro.minutos);
		}
	}

	private static void lerBombeiros(Scanner in){
		for(int i = 0; i < numBombeiros; i++){
			fila.enqueue(new Bombeiro(in.next()));
		}
	}

	private static void inicializar(){
		fila = new LinkedListQueue<>();
		eventos = new Evento[501];

		numEventos = 0; 
	}

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int flag = in.nextInt();
		numBombeiros = in.nextInt();

		inicializar();
		lerBombeiros(in);

		if(flag == 1){
			mostrarNumeroEventos(in);
		}else{
			simular(in);
			if(flag == 2) mostrarEventos();
			else mostrarBombeiros();
		}
	}
}