package Module04.polymorphism.fields;

class InterestCalculation {
	public float interestRate = 8.5f;
	double balance = 10000;
	public void calcInterest() {
		System.out.println("Annual Interest1 : "+(balance*interestRate)/100);
		
	}
}

class NewCalculation extends InterestCalculation{
	public float interestRate = 7.0f;
	double balance = 10000;
	public void calcInterest() {
		System.out.println("Annual Interest2 : "+(balance*interestRate)/100);
		
	}
}

public class HidingFieldsTest {
	public static void main (String [] args) {
		
		NewCalculation nc = new NewCalculation ();
		System.out.println ("Calculating with interest Rate : "+nc.interestRate);
		nc.calcInterest();
		
		InterestCalculation ic = new NewCalculation();
		System.out.println ("Calculating with interest Rate : "+ic.interestRate);
		ic.calcInterest();
		
	}
}


