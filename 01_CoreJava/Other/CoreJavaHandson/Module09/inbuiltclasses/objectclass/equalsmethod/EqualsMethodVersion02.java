package Module09.inbuiltclasses.objectclass.equalsmethod;

import Module04.inheritance.version01.BankAccount;

class SavingsAccount extends BankAccount {
		private float interestRate;
	  	SavingsAccount (String name, float curBal, float interestRate) {
	    			this.interestRate = interestRate;
	  	}
	  	float getInterestRate(){
	  		return interestRate;
	  	}
	  	
		public boolean equals (SavingsAccount s) {
	    	if (getInterestRate() == s.getInterestRate()){
	    		return true;
	    	}
			else{
	    		return false;
			}
		}
}
public class EqualsMethodVersion02 {
	public static void main (String [] args ) {
    			SavingsAccount sa1 = new SavingsAccount ("jack", 1000.0f, 8.75f);
    			SavingsAccount sa2 = new SavingsAccount ("jack", 1000.0f, 8.75f);
    			if (sa1.equals (sa2)) {
      				System.out.println ("sa1 and sa2 are equal");
			}
			else {
      				System.out.println ("sa1 and sa2 are not equal");
			}
  	}
}
