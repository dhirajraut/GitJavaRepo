package Module08.exceptions.customexceptions;

class BankException extends Exception{
	
    public String toString(){
		return "NotEnoughBalance";
   }
}
class Banking {
	int balance = 10000;
	public void withdraw(float amt)throws BankException{
			if((balance-amt)<5000)
				throw new BankException();
			else{
				balance-=amt;
				System.out.println("Sucessfully withdrawn");
			}
	}
	public static void main(String[] args){
		Banking b = new Banking();
	
		try {
			b.withdraw(6000);
		} catch (BankException e) {
			System.out.println("Cannot withdraw....Not Enough Balance..");
		}			 
	}
}

