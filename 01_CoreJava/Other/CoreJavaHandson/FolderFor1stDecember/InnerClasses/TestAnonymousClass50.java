
public class TestAnonymousClass50
	{	public static void main(String [] argv)
		{	
			// Anonymous Class
			BankAccount50 ba = new BankAccount50(101, "Ramesh", 5000)
				{	private final float MAXAMT = 500; 
					public void withdraw(float amt) throws Exception
						{	if (this.getAccBal()-MAXAMT>amt)
								super.withdraw(amt);
							else
								throw new Exception("Not enough balance.");
						}
					public void deposite(float amt) 
						{	super.deposite(amt);	}
					
					public String toString()
						{	return "Number:"+getAccNo()+" Name"+getAccNm()+" Balance"+getAccBal();}
					
					public void myMethod()
						{}
				};
				
			System.out.println("Bare object:"+ba);
			ba.deposite(1000);
			System.out.println("Object after deposite:"+ba);
			
			//The non-overridden method is not accessible here.
			//ba.myMethod();
		}
	}

class BankAccount50
	{	private int accNo;
		private String accNm;
		private float accBal;
		
		public BankAccount50(int accNo, String accNm, float accBal)
			{	this.accNo = accNo;
				this.accNm = accNm;
				this.accBal = accBal;
			}

		public void withdraw(float amt) throws Exception
			{	accBal-=amt;	}
		
		public void deposite(float amt)
			{	accBal+=amt;	}
		
		public void setAccBal(float amt)
			{	accBal=amt;	}
		
		public float getAccBal()
			{	return accBal;	}

		public String getAccNm()
			{	return accNm;	}

		public int getAccNo()
			{	return accNo;	}
	}