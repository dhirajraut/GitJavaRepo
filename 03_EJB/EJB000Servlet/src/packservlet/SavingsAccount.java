package packservlet;

public class SavingsAccount {
		private int accNo;
	  private String accNm;
		private float accBal;
		
		public SavingsAccount(int accNo, String accNm, float accBal) {
				this.accNo = accNo;
				this.accNm = accNm;
				this.accBal = accBal;
			}
		
		public float getBalance(int accNo) {
				return accBal;
			}
		
		public void deposit(float amount) {
				accBal += amount;
	}
}
