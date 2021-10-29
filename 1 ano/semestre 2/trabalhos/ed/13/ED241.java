import java.util.Scanner;
import java.util.LinkedList;

public class ED241{
	static BSTMap<String, BSTree<String>> students;
	static BSTMap<String, Integer> problems;
	static BSTMap<String, Integer> problemsAccepted;

	public static void read(Scanner in,int n){
		for(int i = 0; i < n ; i++){
			String tmpName = in.next();
			String tmpProbName = in.next();
			String tmpFeedback = in.next();

			if (students.get(tmpName) == null){
				students.put(tmpName,new BSTree<>());
			}
			if (problems.get(tmpProbName ) == null) problems.put(tmpProbName ,1);
			else problems.put(tmpProbName ,problems.get(tmpProbName ) + 1);

			if (tmpFeedback.equals("Accepted")){
				students.get(tmpName).insert(tmpProbName);
				if (problemsAccepted.get(tmpProbName) == null) problemsAccepted.put(tmpProbName ,1);
				else problemsAccepted.put(tmpProbName ,problemsAccepted.get(tmpProbName ) + 1);
			}
		}
		return;
	}

	public static void printAllSolved(){
		LinkedList<String> list = students.keys();

		for(String s : list){
			if (students.get(s).numberNodes() == problems.size() ) System.out.println(s);
		}
	}

	public static void printAccepted(){
		LinkedList<String> list = problemsAccepted.keys();

		for(int i = 0; i < list.size();i++){
			String tmp = list.get(i);
			float ratio = (float) problemsAccepted.get(tmp) / (float) problems.get(tmp);
			if(ratio>=0.5) System.out.println(tmp);
		}
		return;
	}

	public static void printHighest(){
		LinkedList<String> list = problems.keys();
		String highest = "";
		int max = -1;

		for(int i = 0; i < list.size();i++){
			int tmp = Math.max(max,problems.get(list.get(i)));
			if(tmp != max){
				highest = list.get(i);
				max = tmp;
			} 
		}
		System.out.println(highest + " " + max);
		return;
	}

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int flag = in.nextInt(),n  = in.nextInt();
		students = new BSTMap<>();
		problems = new BSTMap<>();
		problemsAccepted = new BSTMap<>();

		read(in,n);
		if(flag == 1){
			//readStudents(in,n);
			System.out.println(students.size());
		}else if (flag == 2) printHighest();
		//readProblems(in,n);
		else if( flag == 3) printAccepted();
		else printAllSolved();
	}
}