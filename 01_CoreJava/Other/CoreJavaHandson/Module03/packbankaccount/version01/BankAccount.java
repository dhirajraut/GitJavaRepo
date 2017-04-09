package Module03.packbankaccount.version01;

public class BankAccount {
	 int id;
	 float curBal=0;
  	 String name;

  	
  	 void deposit (float amt) {
    			curBal += amt; 
  	}
  	 void withdraw (float amt) { 
    			curBal -= amt;	
  	}
  	 
}
