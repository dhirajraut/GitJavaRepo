/*
 * BankAccount outer class
 * 		The constructor, withdraw and deposite creates object of inner class
 * 
 * 		Unlike 'static inner' the object of inner class can not be created stand alone
 * 		It must always associate with object of the outer.
 * 
 * 		The private elements of outer are implecitely accessible within inner.
 * 		The private elements of inner are explicitely accessible within outer.
 * 
 * 		Inner does not allow static but allows final static.
 * 		Observe lines 56 to 59.
 * 
 */

public class TestInnerClass20
	{	public static void main(String [] argv)
			{	BankAccount ba = new BankAccount();
				System.out.println("Empty ba:"+ba);	// Output:Empty ba:From outer:100 From inner:No operation5000
				
				ba.deposite(1000);
				System.out.println("ba:"+ba);	// Output:ba:From outer:100 From inner:Deposite6000
				
				ba.withdraw(2000);
				System.out.println("ba:"+ba);	// Output:ba:From outer:100 From inner:Withdraw4000
				
				// A way to create inner object
				ba.lastAct = ba.getAction();
				ba.lastAct = ba.new Action("In main", 0);
				System.out.println("ba:"+ba);	// Output:ba:From outer:100 From inner:In main4000
				
				//ba.Action a = ba.getAction(); Not allowed.  Stand alone object can not be created.
				// A way to invoke any method of inner
				ba.new Action().showInAction();	// Output:From inner class
			}
	}

class BankAccount
	{	private long number;
		private long balance;
		public Action lastAct;
		
		public BankAccount()
			{	number = 100;
				balance = 5000;
				lastAct = new Action("No operation", 0);
			}
		
		public Action getAction()
			{	return this.new Action("In getAction", 0);	}
		
		public class Action
			{	private String act;
				private long amount;
				
				// *******
				//public static int statInt; Inner class must not have static fields
				public static final int statInt=0; // While static final is allowed.
											// This final field must be initialized here.
											// Its lazy initialization is not allowed.
				// *******
				
				public Action()
					{	this(	"No-arg Constructor", 0);	}
				
				public Action(String act, long amount)
					{	this.act = act;
						this.amount = amount;
						// statInt = 10; Lazy initialization not allowed.
					}
				
				// Even private fields of outer are accessible in inner
				public String toString()
					{	return "From outer:"+number+" From inner:"+act;	}
				
				public void showInAction()
					{	System.out.println("From inner class");	}
			}
		
		public void deposite(long amount)
			{	balance += amount;
			
				// Both these syntax are allowed.
				//lastAct = new Action("Deposite", amount);
				lastAct = this.new Action("Deposite", amount);
			}
		
		public void withdraw(long amount)
			{	balance -= amount;
				lastAct = new Action("Withdraw", amount);
			}
		
		// Outer class accessing elements of inner only through explicite reference.
		// Through this reference, private fields of outer are also accessible.
		public String toString()
			{	return lastAct.toString()+ lastAct.act;	}
	}