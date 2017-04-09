package FolderFor1stDecember.Concurrency.Semaphore;
public class BankAccount
	{	private int accNo;
		private String accNm;
		private float accBal;

		private static int accCount=0;
		public BankAccount(String accNm, float accBal)
			{	accNo = ++accCount;
				this.accNo = accNo;
				this.accNm = accNm;
			}
		public void deposite(float amt)
			{	accBal += amt;	}
		public void withdraw(float amt)
			{	accBal -= amt;	}
		public int getAccNo()
			{ 	return accNo;	}
		public String getAccNm()
			{	return accNm;	}
		public void setAccNm(String nm)
			{	accNm = nm;	}
		public float getAccBal()
			{	return accBal;	}
		public static int totalAccounts()
			{	return accCount;	}
	}
