package Module12.generics;

import Module04.inheritance.version01.*;

class ManageAccounts {
	BankAccount BankList[];
  	int size, top;
  	ManageAccounts(int s) {
			 size = s;
			 BankList = new BankAccount [size];
			 top = -1;
	}
  	void addNewAccount(BankAccount account) { 
  		BankList [ ++top ] = account;
	}
  	BankAccount showAccount() {
			 return BankList [ top-- ];
	}
}
public class TestManageAccounts {
  	public static void main (String args[]) {
  		ManageAccounts ma = new ManageAccounts(2); 
    			ma.addNewAccount(new BankAccount());
    			ma.addNewAccount(new SavingsAccount());
    			for(int i=0; i<ma.size; i++) {
     				 System.out.println(ma.showAccount());
    			}
  	}
}


