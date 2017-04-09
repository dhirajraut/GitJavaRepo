package entities;

public class Account {
	private int id;
	private String accountHolder;
	private double balance;
	private Customer customer;
	private char accountType;

	public char getAccountType() {
		return accountType;
	}
	public void setAccountType(char accountType) {
		this.accountType = accountType;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccountHolder() {
		return accountHolder;
	}
	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public boolean deposit(double amount) {
		balance += amount;
		return true;
	}
	public boolean withdraw(double amount) {
		balance -= amount;
		return true;
	}
}
