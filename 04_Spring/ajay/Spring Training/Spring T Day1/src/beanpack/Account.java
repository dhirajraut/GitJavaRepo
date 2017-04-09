package beanpack;

public  abstract class Account {

	private int acctId;
	private Customer cust;
	private double balance;
	public int getAcctId() {
		return acctId;
	}
	public void setAcctId(int acctId) {
		this.acctId = acctId;
	}
	public Customer getCust() {
		return cust;
	}
	public void setCust(Customer cust) {
		this.cust = cust;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public Account(int acctId, Customer cust, double balance) {
		super();
		this.acctId = acctId;
		this.cust = cust;
		this.balance = balance;
	}
	public Account() {
		
	}
	public abstract void withdraw();
	//public abstract void withdraw();
}