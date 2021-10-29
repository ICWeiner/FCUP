/* -----------------------------------
  Estruturas de Dados 2020/2021
  Jogo da Vida [ED088]  
  Completado por Diogo Nunes
----------------------------------- */

import java.util.Scanner;

// Classe para representar um jogo
class Game {
    final char DEAD  = '.';  // Constante que indica uma celula morta
    final char ALIVE = 'O';  // Constante que indica uma celula viva
    private int rows, cols;  // Numero de linhas e colunas
    private char m[][];      // Matriz para representar o estado do jogo

    // Construtor: inicializa as variaveis tendo em conta a dimensao dada
    Game(int r, int c) {
    	rows = r;
    	cols = c;
    	m = new char[r][c];
    }

    // Metodo para ler o estado inicial para a matriz m[][]
    public void read(Scanner in) {
	for (int i=0; i<rows; i++)
	    m[i] = in.next().toCharArray();
    }

    private void read(char[][] next){
        for(int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                m[i][j] = next[i][j];
            }
        }
    }
    
    // Metodo para escrever a matriz m[][]
    public void write() {
        for(int i = 0; i < rows; i++){
            String buf = "";
            for( int j = 0; j < cols; j++){
                buf += m[i][j];
            }
            System.out.println(buf);
        }
    }

    // Deve devolver o numero de celulas vivas que sao vizinhas de (y,x)
    public int countAlive(int y, int x) {
    	int count = 0;
        int xx,yy,xLimit,yLimit;
        //Definicao de boundaries para limites superiores e/ou inferriores
        if(x == 0) xx = 0;
        else xx = x - 1;

        if(y == 0) yy = 0;
        else yy = y - 1;

        if (x == rows - 1) xLimit = rows ;
        else xLimit = x + 2;

        if (y == cols - 1) yLimit = cols ;
        else yLimit = y + 2;

    	//System.out.println(yLimit+" "+ xLimit);

        for(int i = xx; i < xLimit; i++){
            for(int j = yy; j <  yLimit; j++){
                //System.out.println(xx+" "+ yy);
                //System.out.println("posicao " + i + " "  + j + " contem " + m[i][j]);
                if( m[i][j] == 'O' && (i != x || j != y)) count++;
                //System.out.println(i +" " + j);
                }
            }
        return count;
        }


    // Deve fazer uma iteracao: cria nova geracao a partir da actual
    public void iterate(){
        char next[][] = new char[rows][cols];
        int count = 0;
        for(int i = 0; i < rows; i++){ 
            for (int j = 0; j < cols; j++){
                int nearby = countAlive(j,i);
                char current = m[i][j];

                //System.out.println("posicao : "+ i +" " + j + " vizinhos " + nearby + " valor " + current);

                if( current == ALIVE && nearby < 2) next[i][j] = DEAD;
                else if (current == ALIVE && nearby > 3) next[i][j] = DEAD;
                else if (current == DEAD && nearby == 3) next[i][j] = ALIVE;
                else next[i][j] = m[i][j];

                //System.out.println(" novo estado " + next[i][j]);
            }
        }
        //System.out.println(count);
        read(next);
        //write();
    }
}

// Classe principal com o main()
public class ED088 {
    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);

	// Ler linhas, colunas e numero de iteracoes
	int rows = in.nextInt();
	int cols = in.nextInt();
	int n    = in.nextInt();

	// Criar objecto para conter o jogo e ler estado inicial
	Game g = new Game(rows, cols);
	g.read(in);
    //System.out.println(g.countAlive(2,1));
    for( int i = 0; i < n; i++){
        g.iterate();
        }
    g.write();
    }
}