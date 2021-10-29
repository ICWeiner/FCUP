
class Aluno {
	public static int contador = 0;
	String nome;
	int numero;


	Aluno(){
		nome="indefinido";
		numero=-1;
		contador++;
	}

	Aluno(String n, int mec){
		nome = n;
		numero = mec;
		contador++;
	}

	public String toString(){
		return "(" + nome + "," + numero + ")";
	}

	int ano(){
		return numero / 100000;
	}

}

public class TestAluno {
	public static void main(String[] args){
		Aluno a = new Aluno();

		Aluno b = new Aluno();

		Aluno c = b;

		Aluno d = new Aluno();

		Aluno e = new Aluno("Diogo", 202007895);

		Aluno g = new Aluno("Ana",201408762);

		Aluno h = new Aluno("Bruno",201508145);

		b.nome = "modificado";

		int n = 3;

		Aluno[] v = new Aluno[n];
		for(int i = 0; i < n; i++){
			v[i] = new Aluno();
			System.out.println("v[" + i + "] = " + v[i]);
		}

		v[0].nome = "Pedro";
		

		System.out.println("a.nome = "+ a.nome);
		System.out.println("a.numero = "+ a.numero);

		System.out.println("a =" + a);
		System.out.println("b = " + b);
		System.out.println("c = " + c);
		System.out.println("d = " + d);
		System.out.println("e = " + e);
		System.out.println(g.ano() + " " + h.ano());
		System.out.println("contador = " + Aluno.contador);
	}
}