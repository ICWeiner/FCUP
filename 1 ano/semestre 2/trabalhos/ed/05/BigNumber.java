class BigNumber{
	private int numbers[];
	private int length;

	BigNumber(){
		numbers = new int[100];
		length = 0;
	}

	BigNumber(String n){
		numbers = new int[100];
		length = n.length();



		for (int i = length - 1 ,j = 0; i >= 0  ; i-- ,j++ ) {
			char number = n.charAt(i);
			numbers[j] = Character.getNumericValue(number);
			//System.out.println(n.charAt( length - i) + " " + i);
			//System.out.println(numbers[i] + " " + i);
		}
	}


	public boolean equals(BigNumber n){
		for(int i = 0; i < length ; i++){
			if( this.numberAtPos(i) != n.numberAtPos(i)) return false;
		}
		return true;
	}

	public BigNumber add(BigNumber n){//done?
		BigNumber outNumber = new BigNumber();

		int outLength;

		if (length <= n.getLength()) outLength = n.getLength();
		else outLength = length;

		outLength--;

		for(int i = 0;(i <= outLength);i++){
			outNumber.addDigit(i,this.numberAtPos(i) + n.numberAtPos(i));

			//System.out.println(i  + ":" + this.numberAtPos(i) + " + " +   n.numberAtPos(i) +" = " + outNumber.getDigit(i));

			if(outNumber.getDigit(i) >= 10){
				outNumber.addDigit(i,-10);
				outNumber.addDigit( (i +1), 1);
			}
			outNumber.increaseLength();
		}
		//outNumber.checkLast();

		return outNumber;

	}

	public BigNumber multiply(BigNumber n){
		// DO THIS LATER IF YOU WANT TO


		/*int productLength = this.getLength();


		BigNumber products[] = new BigNumber[n.getLength()/*productLength];
		BigNumber outNumber = new BigNumber();
		BigNumber carryNumber = new BigNumber();


		for(int i = 0 , offset = 0; i < n.getLength() /*productLength; i++, offset++){
			
			products[i] = new BigNumber();
			
			for (int j = 0; j < productLength/* n.getLength(); j++){
				int tmp, pos = j + offset;

				tmp =  this.getDigit(j) * n.getDigit(i);
				System.out.println(this.getDigit(j) + " " + n.getDigit(i) + " = " + tmp);

				while (tmp>=10){
					carryNumber.addDigit(pos + 1, 1);
					tmp-=10;
					carryNumber.adjustLength((pos +1));
					//if(carryNumber.getLength() < (pos +1)) carryNumber.increaseLength( (pos + 1) - carryNumber.getLength());
					//System.out.println(products[i].getDigit(pos + 1));
				}

				products[i].addDigit(pos,tmp);
				//System.out.println(products[i].getDigit(pos));
			}
			products[i].increaseLength(n.getLength() + offset);
			//products[i].adjustLength(n.getLength() + offset);
			
		}

		for (int i = 0; i < n.getLength()/*productLength ; i++ ) {
			//System.out.println(products[i]);
			outNumber = outNumber.add(products[i]);
		}

		outNumber = outNumber.add(carryNumber);
		
		outNumber.adjustLength(carryNumber.getLength());
		//System.out.println(carryNumber.getLength()); //arranjar length final
		return outNumber;*/
	}

	private void adjustLength(int x){
		//System.out.println(x + " " + this.getLength());
		if(this.getLength() < x) this.increaseLength(x - this.getLength());
	}

	private void checkLast(){
		if     (this.getDigit(length + 1) != 0) this.increaseLength();
		else if(this.getDigit(length + 1) == 0) this.decreaseLength();
	}

	private void addDigit(int pos, int x){
		this.numbers[pos] += x; 
	}

	private int getDigit(int pos){
		return this.numbers[pos];
	}

	private void increaseLength(){ this.length++;}

	private void increaseLength(int i){
		//System.out.println(i);
		this.length+= i;
	}

	private void decreaseLength(){ this.length--;}

	private int getLength(){ return length; } 

	private int numberAtPos(int x){ return numbers[x]; }

	public String toString(){
		String ans = "";

		//System.out.println("length of " + " is " + length);

		for(int i = length -1 ; i >= 0  ; i-- ){
			ans += this.numberAtPos(i);
		}

		return ans;
	}
}