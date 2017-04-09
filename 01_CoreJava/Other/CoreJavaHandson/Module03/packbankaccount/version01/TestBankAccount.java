package Module03.packbankaccount.version01;

class TestBankAccount {

	public static void main(String[] args) {
		BankAccount ba = new BankAccount ();
		System.out.println ("Previous Balance :" + ba.curBal);
		ba.deposit (5000);
		System.out.println ("Balance after depositing Rs.5000/-  :" + ba.curBal);
		ba.withdraw(1000);
		System.out.println ("Balance after withdrawing Rs.1000/- :" + ba.curBal);
	}
}
