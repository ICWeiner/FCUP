import java.util.Scanner;

/* -----------------------------------
  Estruturas de Dados 2020/2021
  Sopa de letras[ED088]  
  Feito por Diogo Nunes
----------------------------------- */
class LetterGame{
	final char KEEP = 'O';
	final char DISCARD = '.';
	int lins;
	int cols;
	char m[][];
	char next[][];

	LetterGame(int lins, int cols){
		this.lins = lins;
		this.cols = cols;
		m    = new char[lins][cols];
		next = new char[lins][cols];
	}

	public void read(Scanner in) {
		for (int i = 0; i < lins; i++)
	    	m[i] = in.next().toCharArray();
    }

    //Percorre todo o array a procura da primeira ou ultima letra de uma das palavras introduzidas
    public void findWords(char[][] words){//TO-DO: FIGURE OUT HOW TO EITHER ADD REVERSE OF EVERY WORD TO CHAR ARRAY OR BE ABLE TO REVERSE SEARCH THE WORDS
    	for( int i = 0; i < lins; i++){
    		for( int j = 0; j < cols; j++){
    			for( int k = 0; k < words.length; k++){
    				if(m[i][j] == words[k][0]/* || m[i][j] == words[k][last]*/){
    					//System.out.println("Found first or last character that matched: "+  m[i][j] +" "+ words[k][last] + " at " + i + " " + j);
    					checkWord(words[k],i,j,0,1);
    					checkWord(words[k],i,j,1,0);
    					checkWord(words[k],i,j,0,-1);
    					checkWord(words[k],i,j,-1,0);
    				}
    			}
    		}
    	}
    	makeNext();
    }

    //verifica se a palavra esta toda seguida no array
    private void checkWord(char[] word,int x,int y,int incx,int incy){
		int length = word.length;

		for( int i = 0, xx = x, yy = y; i < length; i++, xx+=incx, yy+=incy){
			//System.out.println(xx + " " + yy);
			if(xx < lins && yy < cols && xx >= 0 && yy >= 0){
				if( word[i]!= m[xx][yy]/* || word[length - i] == m[xx][yy])*/) return;
				//System.out.println(word[i] + " " + m[xx][yy] + " " + i);
			}else return;
		}
		keepWord(length,x,y,incx,incy);
    }

    //marca as posicoes todas da palavra para serem mais tarde guardadas
	private void keepWord(int length, int x, int y, int incx, int incy){
		for( int i = 0, xx = x, yy = y; i < length ; i++, xx+=incx, yy+=incy){
			//System.out.println(xx + " " + yy );
			if(xx < lins && yy < cols  && xx >= 0 && yy >= 0){
				next[xx][yy] = KEEP;
				//System.out.println("kept");
			}	
    	}
	}

	//Discarda tudo o o que nao esta marcado para ser guardado
	private void makeNext(){
		for( int i = 0; i < lins; i++){
    		for( int j = 0; j < cols; j++){
    			if(next[i][j] != KEEP) m[i][j] = DISCARD;
    		}
    	}
	}


	public String toString(){
		String ans = ""; 
		for (int i = 0; i < lins; i++) {
			for (int j = 0; j < cols; j++) ans += m[i][j];
			ans += "\n";
		}
		return ans;
	}
}

public class ED015 {
	public static void main(String[] args){

	Scanner in = new Scanner(System.in);
	int gameCount = 0;
	LetterGame letterGame[] = new LetterGame[100];

	int lins = in.nextInt();
	int cols = in.nextInt();

	while(lins!=0){
		char[][] words;
		int wordCount;

		letterGame[gameCount] = new LetterGame(lins,cols);
		letterGame[gameCount].read(in);

		wordCount = in.nextInt();
		words = new char[wordCount][50];

		for(int i = 0; i < wordCount; i++) words[i] = in.next().toCharArray();

		letterGame[gameCount].findWords(words);
		lins = in.nextInt();
		cols = in.nextInt();
		gameCount++;
		}

	for(int i = 0; i < gameCount; i++){
		System.out.print("Input #"+ (i+1) +"\n"+letterGame[i]);
	}
	}
}