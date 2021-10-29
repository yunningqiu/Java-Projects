import java.lang.Number;
import java.math.BigInteger;

public final class Rational extends Number{
	private BigInteger num;
	private BigInteger denom;

	public Rational(int numerator, int denominator){
		if (denominator==0){
			System.out.println("denominator cannot be zero!");
			throw new IllegalArgumentException("Divide by zero error");
		}
		// if denominator is zero, throw error message

		num = BigInteger.valueOf(numerator);
		denom = BigInteger.valueOf(denominator);
	}
	// constructor 1: assign with new BigInteger converted from int
	// valueOf() convert int to BigInteger

	public Rational(BigInteger numerator, BigInteger denominator){
		BigInteger gcd = numerator.gcd(denominator);
		// get greatest common divisor of numerator and denominator

		num = numerator.divide(gcd);
		denom = denominator.divide(gcd);
		// get the simplest numerator and denominator

		if (num.equals(BigInteger.ZERO.subtract(numerator.abs())) 
			&& denom.equals(BigInteger.ZERO.subtract(numerator.abs())))
		{
			num = BigInteger.ZERO.subtract(num);
			denom = BigInteger.ZERO.subtract(denom);
		}
		// if both numerator and denominator are negative, turn them both to positive
	}
	// constructor 2

	//-----------------------------------------------------------------------------------
	//implement equals and toString inherited from java.lang.Object
	public boolean equals(Object obj){
		if (obj instanceof Rational){
			if ((this.subtract((Rational)obj)).getNumerator().equals(BigInteger.ZERO))
				return true;
			else
				return false;
		}
		// check if the object is rational

		else
			return false;
	}
	// if difference is zero, they are the same

	public String toString(){
		if(num.equals(BigInteger.ZERO))
			return "(" + num + "/" + denom + ") = 0";
		// if the value is 0, print =0

		else if(denom.equals(BigInteger.ONE))
			return"(" + num + "/" + denom + ") = " + num;
		// if the denominator is 1, print =numerator

		return "(" + num + "/" + denom + ")";
		// print the rational number as a fraction
	}


	//-----------------------------------------------------------------------------------
	//ovride four abstract method from java.lang.Number
	public double doubleValue(){
		return num.divide(denom).doubleValue();
	}
	//convert the big integer to a double

	public float floatValue(){
		return (float)doubleValue();
	}

	public int intValue(){
		return (int)doubleValue();
	}

	public long longValue(){
		return (long)doubleValue();
	}


	//-----------------------------------------------------------------------------------
	// appropriate member functions named add, subtract, multiply, and divide
	public Rational add(Rational r){
		BigInteger d = denom.multiply(r.getDenominator());
		BigInteger n = num.multiply(r.getDenominator()).add(r.getNumerator().multiply(denom));
		return new Rational(n, d);
	}
	// a/b + c/d = (ad+bc)/bd

	public Rational subtract(Rational r){
		BigInteger d = denom.multiply(r.getDenominator());
		BigInteger n = num.multiply(r.getDenominator()).subtract(r.getNumerator().multiply(denom));
		return new Rational(n, d);
	}
	// a/b - c/d = (ad-bc)/bd

	public Rational multiply(Rational r){
		BigInteger d = denom.multiply(r.getDenominator());
		BigInteger n = num.multiply(r.getNumerator());
		return new Rational(n, d);
	}
	// a/b * c/d = ac/bd

	public Rational divide(Rational r){
		BigInteger d = denom.multiply(r.getNumerator());
		BigInteger n = num.multiply(r.getDenominator());
		return new Rational(n, d);
	}
	// a/b / c/d = ad/bc


	//-----------------------------------------------------------------------------------
	// the getter methods getNumerator and getDenominator
	public BigInteger getNumerator(){
		return num;
	}

	public BigInteger getDenominator(){
		return denom;
	}


	//factory methods 
	public static Rational intToRational(int num){
		BigInteger n = BigInteger.valueOf(num);
		BigInteger d = BigInteger.valueOf(1);
		return new Rational(n,d);
	}
	// convert an int to Rational with denominator 1

	public static Rational BigIntegerToRational(BigInteger num){
		return new Rational(num, BigInteger.ONE);
	}
	// convert a BigInteger to Rational with denominator ONE


	public static void main(String[] args){
		// BigInteger b = BigInteger.valueOf(27);
		// b = b.add(BigInteger.ONE);
		// System.out.println("b is" + b);

		Rational r1 = new Rational(50, 100);
		BigInteger b1 = new BigInteger("50");
		BigInteger b2 = new BigInteger("100");
		Rational r2 = new Rational(b1, b2);
		System.out.println(r1.add(r2));
		System.out.println(r1.subtract(r2));
		System.out.println(r1.multiply(r2));
		System.out.println(r1.divide(r2));
		System.out.println(r1.equals(r2));
		System.out.println(r1.doubleValue());
		System.out.println(r1.intValue());
		System.out.println(r1.floatValue());
		System.out.println(r1.longValue());
		int i = 120;
		System.out.println(BigIntegerToRational(b1));
		System.out.println(intToRational(i));




	}





}