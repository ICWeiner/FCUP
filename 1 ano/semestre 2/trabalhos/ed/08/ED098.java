/* -----------------------------------
  Estruturas de Dados 2020/2021
  Triagem de Manchester [ED098]  
----------------------------------- */

import java.util.Scanner;
import java.util.LinkedList;

// Classe para representar um Doente
class Doente {
	public String nome;     // Nome
	public int chegada;     // Tempo de chegada
	public int atendimento; // Tempo que demora a ser atendido
	public int entrada;     // Tempo em que comecou a ser atendido

	// Construtor: inicializa os atributos
	Doente(String n, int c, int a) {
	nome        = n;
	chegada     = c;
	atendimento = a;
	entrada     = -1;
	}
}

// Classe para representar uma fila de atendimento de uma cor (necessaria para criar array)
// (nota: Java nao permite criacao direta de array de genericos)
class FilaAtendimento {
	public MyQueue<Doente> fila;

	FilaAtendimento() {
	fila = new LinkedListQueue<Doente>();
	}
}

// Classe para representar uma equipa da urgencia
class Equipa {
	int numDoentes;       // Numero de doentes que atenderam
	int totalAtendimento; // Total de tempo passado a atender
	int livre;            // Tempo em que ficam livres para poder atender novo doente

	Equipa() {
		numDoentes       = 0;
		totalAtendimento = 0;
		livre            = 0;
	}

	// Novo doente d comecou a ser atendido num dado tempo t nesta equipa
	void novoDoente(Doente d, int t) {
		this.totalAtendimento += d.atendimento;
		this.livre = t + d.atendimento;
		this.numDoentes++;
	}
}

// Classe principal que contem o metodo main
class ED098 {
	// Constantes que nao mudam durante o programa: numero de cores e seus nomes
	private static final int NUM_CORES = 5;
	private static final String[] CORES = {"Vermelho","Laranja","Amarelo","Verde","Azul"};

	private static int numEquipas;               // Numero de equipas
	private static int numDoentes;               // Numero total de doentes
	private static FilaAtendimento emEspera[];   // Array com uma fila para cada cor
	private static LinkedList<Doente> atendidos; // Lista de doentes ja atendidos
	private static Equipa equipas[];             // Equipas da urgencia

	// Metodo para devolver indice de uma cor representada pela string s
	private static int indiceCor(String s) {
		for(int i = 0; i < NUM_CORES; i++){
			if (CORES[i].equals(s)) return i;
		}
		return -1;
	}

	// Ler os doentes a partir do input e coloca-los nas respetivas filas
	private static void lerDoentes(Scanner in) {
		while (in.hasNext()) {
			String nome     = in.next();
			String cor      = in.next();
			int chegada     = in.nextInt();
			int atendimento = in.nextInt();
			Doente doente = new Doente(nome,chegada,atendimento);
			//System.out.printf("Li [%s,%s,%d,%d]%n", nome, cor, chegada, atendimento);
			emEspera[indiceCor(cor)].fila.enqueue(doente);
			numDoentes++;
		}
	}

	// Mostrar numero de doentes em cada cor (necessario para flag==0)
	private static void mostrarCores() {
		System.out.println("------------");
		System.out.println("Cores     ND");
		System.out.println("------------");
		
		for (int i = 0; i < NUM_CORES ; i++){
			System.out.printf("%8s %3d%n",CORES[i],emEspera[i].fila.size());//CORES[i] + "   " +emEspera[i].fila.size());
		}
		System.out.println("------------");
		System.out.println("Numero doentes atendidos: " + numDoentes);
	}
	

	// Mostrar estatisticas dos doentes atendidos (necessario para flag==1 e flag==2)
	private static void mostrarAtendidos() {
		int esperaTotal = 0;
		System.out.println("---------------------------");
		System.out.println("Lista dos doentes atendidos");
		System.out.println("---------------------------");
		// itera sobre todos os doentes jÃ¡ atendidos (instruÃ§Ã£o for-each)
		for (Doente d : atendidos) {
			int espera = d.entrada - d.chegada;
			int saida  = d.entrada + d.atendimento;
			System.out.println(d.nome + " " + d.chegada + " " + espera  + " " + saida);
			esperaTotal += espera;
		}
		System.out.println("---------------------------");
		System.out.printf("Tempo medio de espera: %.1f%n", ((float) esperaTotal / numDoentes));
	}

	// Mostrar estatisticos das equipas (necessario para flag==2)
	private static void mostrarEquipas() {
		System.out.println("-----------------------");
		System.out.println("Equipa NDoentes MediaTA");
		System.out.println("-----------------------");

		for (int i = 0;i < numEquipas ;i++) {
			System.out.printf("%6d%9d%8.1f%n",i,equipas[i].numDoentes,((float) equipas[i].totalAtendimento / equipas[i].numDoentes));
		}

	}

	// Qual a cor da proxima equipa a ficar livre? 
	private static int proximaEquipaLivre() {
		int proximoTempo = 9999999;
		int proximaEquipa = -1;
		for(int i = 0; i < numEquipas; i++){
			if(equipas[i].livre < proximoTempo){
				proximoTempo = equipas[i].livre;
				proximaEquipa = i;
			}
		}
		//System.out.println(proximaEquipa);
		return proximaEquipa;
	}

	// Qual a cor mais prioritaria com doente ainda por ser atendido no tempo atual?
	private static int proximoDoente(int tempo){
		for(int i = 0; i < NUM_CORES; i++){
			if( !(emEspera[i].fila.isEmpty()) ){
				int proximoTempo = emEspera[i].fila.first().chegada;
				if(proximoTempo <= tempo) return i;
			}
		}
		return -1;
	}

	// TO-DO FIX THIS , o tempo esta a ir para cima de 1440
	private static int proximoTempo(){
		int proximoTempo = 9999999;//magic number,temos de comceçar com algo para comparar
		for(int i = 0; i < NUM_CORES; i++){
			if( !(emEspera[i].fila.isEmpty()) ){
				int tempo = emEspera[i].fila.first().chegada;
				if(proximoTempo > tempo) proximoTempo = tempo;
			}
		}
		//System.out.println(proximoTempo);
		return proximoTempo;
	}

	private static void simularConsulta(int tempo,int e){
		Doente d = emEspera[proximoDoente(tempo)].fila.dequeue();
		equipas[e].novoDoente(d,tempo);
		d.entrada = tempo;
		tempo += d.atendimento;
		atendidos.addLast(d);
	}

	// Simular processo de atendimento pelas varias equipas medicas
	private static void simular() {
		int tempo = 0;
		int i = 0;
		while(atendidos.size() < numDoentes){
			//System.out.println(i);
			int e = proximaEquipaLivre();
			tempo = equipas[e].livre;
			int proximaCor = proximoDoente(tempo);
			if( proximaCor != -1){
				simularConsulta(tempo,e);
			}else{
				tempo = proximoTempo();
				simularConsulta(tempo,e);
			}i++;
		}
	}

	// Inicializar variaveis
	private static void inicializar() {
		numDoentes = 0;

		emEspera = new FilaAtendimento[NUM_CORES];	
		for (int i=0; i<NUM_CORES;i++)
			emEspera[i] = new FilaAtendimento();

		atendidos = new LinkedList<Doente>();

		equipas = new Equipa[numEquipas];
		for (int i=0; i<numEquipas; i++)
			equipas[i] = new Equipa();
		}

		// ----------------------------------------------------------------

	// Funcao principal chamada no inicio do codigo
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int flag   = in.nextInt();
		numEquipas = in.nextInt();
		
		inicializar();
		lerDoentes(in);

		if (flag==0) {
			mostrarCores();
		} else {
			simular();
			if (flag==2) mostrarEquipas(); 
			mostrarAtendidos();
		}
	}
}