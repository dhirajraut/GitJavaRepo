package Module03.packbankaccount.version02;

public class BankAccount {
	private int id;
	private float curBal;
  	private String name;

  	public BankAccount () {
    			curBal = 0; 
  	}
  	public BankAccount(int id, String name, float curBal) {
  		this.id = id;
  		this.name=name;
  		this.curBal=curBal;
  		
  	}
  	public void deposit (float amt) {
    			curBal += amt; 
  	}
  	public void withdraw (float amt) { 
    			curBal -= amt;	
  	}
  	public float getCurBal () {
    			return curBal; 
  	}
  	public void setName(String name) {
  		this.name = name;
  	}
  	public String getName() {
  		return name;
  	}
  	public int getId() {
  		return id;
  	}
}
