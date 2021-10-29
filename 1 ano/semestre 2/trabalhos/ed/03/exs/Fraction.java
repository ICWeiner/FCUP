class Fraction{
	int numerador;
	int denominador;

	Fraction(int numerador, int denominador){
		this.numerador = numerador;
		this.denominador = denominador;
	}

	public Fraction add(Fraction f){
		Fraction outFraction;
		int outNumerador;
		int outDenominador;

		if(this.denominador == f.denominador){
			outNumerador = this.numerador + f.numerador;
			outDenominador = this.denominador;
		}else{
			outNumerador = (this.numerador * f.denominador) + (f.numerador * this.denominador);
			outDenominador = this.denominador*f.denominador;
		}

		outFraction = new Fraction(outNumerador, outDenominador);

		return outFraction;
	}

	public Fraction multiply(Fraction f){
		Fraction outFraction;
		int outNumerador = this.numerador * f.numerador;
		int outDenominador = this.denominador * f.denominador ;

		outFraction = new Fraction(outNumerador, outDenominador);

		return outFraction;
	}

	public void simplify(){
		int a = this.numerador;
		int b = this.denominador;
		int r;

		while (b != 0){
			r = a % b;
			a = b;
			b = r;
		}
		this.numerador = this.numerador / a;
		this.denominador = this.denominador / a;
	}

	public String toString(){
		int floor = (int) numerador/denominador;
		int remainder = numerador - (denominador* floor);

		if(floor > 0){
			return(floor + " + " + remainder + "/" + denominador);
		}else{
			return(numerador + "/" + denominador);
		}

		
	}
}