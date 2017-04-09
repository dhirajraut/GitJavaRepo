package Module12.generics;

import Module03.packbankaccount.version01.BankAccount;

class Employee{
	int eno;
	BankAccount ba;
	public String toString(){
		return "i am an employee";
	}
	
}
class TwoGeneric<AnyType1, AnyType2> {
	  AnyType1 a;
	  AnyType2 b;
	  TwoGeneric(AnyType1 x, AnyType2 y) { a = x; b =y; }
	  void showTypes() {
	    System.out.println("Type of AnyType1 : "+a.getClass().getName());
	    System.out.println("Type of AnyType2 : "+b.getClass().getName());
	  }
	  AnyType1 getA() {
	    return a;
	  }
	 AnyType2 getB() {
	    return b;
	  }
	}

public class TwoGenericTest {
	  public static void main (String args[]) {
		BankAccount ba = new BankAccount();
		Employee e = new Employee();
	    TwoGeneric<BankAccount, Employee> tg = 
	      new TwoGeneric<BankAccount,Employee>(ba,e);
	    tg.showTypes();
	    BankAccount x = tg.getA();
	    System.out.println("Value of x : " +x);
	    Employee y = tg.getB();
	    System.out.println("Value of y :" +y);
	  }
	}

