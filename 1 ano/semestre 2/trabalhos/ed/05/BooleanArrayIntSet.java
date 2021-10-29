public class BooleanArrayIntSet implements IntSet {
	private int arraySize;
	private boolean isElem[];
	private int elemCount;

	BooleanArrayIntSet(int maxSize){
		arraySize = maxSize + 1;
		isElem = new boolean[arraySize];
		elemCount = 0;
	}

	public boolean contains(int x){
		return isElem[x];
	}

	public boolean add(int x){
		if ( isElem[x] == true) return false;
		isElem[x] = true;
		elemCount++;
		return true;
	}

	public boolean remove(int x){
		if ( isElem[x] == false) return false;
		isElem[x] = false;
		elemCount--;
		return true;
	}

	public int size(){
		return elemCount;
	}

	public void clear(){
		isElem = new boolean[arraySize];
		elemCount = 0;
	}

   public boolean equals(IntSet s){
      if(elemCount != s.size()) return false;

      for(int i = 0; i < arraySize; i++){
         if(!(s.contains(i) == this.contains(i))) return false;
      }
      return true;
   }

   public IntSet intersection(IntSet s){
     IntSet outSet = new BooleanArrayIntSet(arraySize - 1);

     for(int i = 0	; i < arraySize; i++){
        if (s.contains(i) && this.contains(i)) outSet.add(i);
        //System.out.println(i + " " + s.contains(i) + " " + this.contains(i));
     }

      return outSet;
   }

  public String toString(){
		String ans = "{"; 
		for (int i = 0; i < arraySize; i++) {
			if( this.contains(i)){
				ans+= (i +", ");
			}
		}ans+= "}";

		return ans;
	}
}