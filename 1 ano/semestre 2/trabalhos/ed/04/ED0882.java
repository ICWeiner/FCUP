/* -----------------------------------
  Estruturas de Dados 2018/2019
  Jogo da Vida [ED088]  
----------------------------------- */

import java.util.Scanner;

// Classe para representar um jogo
class Game 
{
  final char DEAD  = '.';  // Constante que indica uma celula morta
  final char ALIVE = 'O';  // Constante que indica uma celula viva
  private int rows, cols;  // Numero de linhas e colunas
  private char m[][];      // Matriz para representar o estado do jogo

  // Construtor: inicializa as variaveis tendo em conta a dimensao dada
  Game(int r, int c) 
  {
    rows = r;
    cols = c;
    m = new char[r][c];
  }

  // Metodo para ler o estado inicial para a matriz m[][]
  public void read(Scanner in) 
  {
    for (int i=0; i<rows; i++)
    {
      m[i] = in.next().toCharArray();
    }
  }
    
  // Metodo para escrever a matriz m[][]
  public void write() 
  {
    for (int i=0; i<rows; i++) 
    {
      for(int j=0; j<cols; j++)
      {
        System.out.print(m[i][j]);    
      }
      System.out.println();
    }
  }


  // Deve devolver o numero de celulas vivas que sao vizinhas de (x,y)
  private int countAlive(int x, int y) 
  {
    int count = 0;
    if((x+1)<rows && m[x+1][y] == ALIVE)                  count++;
    if((x-1)>=0 && m[x-1][y] == ALIVE)                    count++;
    if((y+1)<cols && m[x][y+1] == ALIVE)                  count++;
    if((y-1)>=0 && m[x][y-1] == ALIVE)                    count++;
    if((x+1)<rows && (y+1)<cols && m[x+1][y+1] == ALIVE)  count++;
    if((x-1)>=0 && (y+1)<cols && m[x-1][y+1] == ALIVE)    count++;
    if((x+1)<rows && (y-1)>=0 && m[x+1][y-1] == ALIVE)    count++;
    if((x-1)>=0 && (y-1)>=0 && m[x-1][y-1] == ALIVE)      count++;
    return count;
  }

  // Deve fazer uma iteracao: cria nova geracao a partir da actual
  public void iterate() 
  {
    char[][] proxger = new char[rows][cols];
    for(int i=0;i<rows;i++)
    {
      for(int j=0;j<cols;j++)
        {
          if(m[i][j] == ALIVE)
          {
            if(countAlive(i,j) > 3) proxger[i][j]=DEAD;
            else 
            {
              if (countAlive(i,j) < 2) proxger[i][j]=DEAD;
                else
                {
                  proxger[i][j]=ALIVE;
                }
            }
          }
          else 
          {
            if(countAlive(i,j)==3) proxger[i][j]=ALIVE;
            else 
            {
              proxger[i][j]=DEAD;
            }
          }
        }
    }
    m=proxger;
  }

}

// Classe principal com o main()
public class ED0882 
{
  public static void main(String[] args) 
  {
    Scanner in = new Scanner(System.in);

    // Ler linhas, colunas e numero de iteracoes
    int rows = in.nextInt();
    int cols = in.nextInt();
    int n    = in.nextInt();

    // Criar objecto para conter o jogo e ler estado inicial
    Game g = new Game(rows, cols);
    g.read(in);
    for(int i=0; i<n; i++)
    {
      g.iterate();
    }
    g.write();
  }
}