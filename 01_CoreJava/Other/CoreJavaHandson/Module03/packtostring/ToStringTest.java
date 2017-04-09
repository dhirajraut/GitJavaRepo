package Module03.packtostring;

class BankAccount {
	int accNo;
  	String name;
	float curBal;
  	
	BankAccount (int ano, String nm, float bal) {
    	accNo = ano;
    	name = nm;
    	curBal = bal;
  	}
  	
	public String toString () {
		String str = name + " has balance of :: " + curBal;
    	return str;
  	}
}

public class ToStringTest {
	public static void main (String [] args) {
		BankAccount objectB = null;
		objectB = new BankAccount (10, "John", 5000.0f);
		System.out.println ("Details of objectB : \n" + objectB);
  	}
}

