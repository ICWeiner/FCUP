public class ED248 {
    public static void main(String[] args) {
	int n = 6500;
	/*IntSet s1 = new BooleanArrayIntSet(n);
	IntSet s2 = new BooleanArrayIntSet(n);
	IntSet s3 = new BooleanArrayIntSet(n);
	IntSet s4 = new BooleanArrayIntSet(n);
	IntSet s5 = new BooleanArrayIntSet(n); 
	IntSet s6 = new BooleanArrayIntSet(n);
	IntSet s7 = new BooleanArrayIntSet(n);  
	IntSet s8 = new BooleanArrayIntSet(n); 


	s3 = s1.intersection(s2);
	System.out.println(s3.size());
	System.out.println(s1.add(900));
	System.out.println(s1.add(800));
	System.out.println(s1.add(700));
	System.out.println(s1.add(600));
	System.out.println(s1.add(500));
	System.out.println(s1.add(400));
	System.out.println(s2.add(100));
	System.out.println(s2.add(400));
	System.out.println(s2.add(900));
	System.out.println(s2.add(700));
	System.out.println(s2.add(6500));
	s3 = s1.intersection(s2);
	System.out.println(s3.size());
	System.out.println(s3.contains(700));
	System.out.println(s3.contains(400));
	System.out.println(s3.contains(900));
	System.out.println(s3.contains(800));
	System.out.println(s3.contains(100));
	s4 = s3.intersection(s1);
	System.out.println(s4.size());
	s5 = s4.intersection(s3);
	System.out.println(s5.size());
	System.out.println(s6.add(1));
	System.out.println(s6.add(2));
	System.out.println(s6.add(3));
	System.out.println(s7.add(4));
	System.out.println(s7.add(5));
	System.out.println(s7.add(6));
	s8 = s6.intersection(s7);
	System.out.println(s8.size());
	System.out.println(s8.contains(1));
	System.out.println(s8.contains(2));
	System.out.println(s8.contains(3));
	System.out.println(s8.contains(4));
	System.out.println(s8.contains(5));
	System.out.println(s8.contains(6));*/

	/*System.out.println(s1);
	System.out.println(s2);
	System.out.println(s3);
	System.out.println(s4);
	System.out.println(s5);
	System.out.println(s6);
	System.out.println(s7);
	System.out.println(s8);*/

















	IntSet s1 = new BooleanArrayIntSet(n);
	IntSet s2 = new BooleanArrayIntSet(n);
	IntSet s3;

	boolean tmp;

	s1.add(1);
	s1.add(3);

	s2.add(3);
	s2.add(5);

	s3 = s2.intersection(s1);

	System.out.println(s1.contains(1));

	System.out.println(s3.contains(1));
	System.out.println(s3.contains(3));
	System.out.println(s3.contains(5));



	}
}