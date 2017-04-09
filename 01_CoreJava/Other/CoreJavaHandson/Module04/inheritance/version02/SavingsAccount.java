package Module04.inheritance.version02;


class SavingsAccount extends BankAccount {
  	private boolean isSalaried;
	SavingsAccount (boolean isSal) {
		isSalaried = isSal;	
  	}
	
	public void show(){
		System.out.println("Is the Account salaried ? "+isSalaried);
	}
}