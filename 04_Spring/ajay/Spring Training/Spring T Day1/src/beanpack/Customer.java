package beanpack;

import java.util.HashMap;
import java.util.List;

public class Customer {

	private int custId;
	private String custName;
	private Address add;
	private List accts;
	public Address getAdd() {
		return add;
	}
	public void setAdd(Address add) {
		this.add = add;
	}
	public List getAccts() {
		return accts;
	}
	public void setAccts(List accts) {
		this.accts = accts;
	}
	public Customer()
	{
		setCustId(101);
		setCustName("Default");
	}
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public Customer(int custId,String custName)
	{
		this.custId=custId;
		this.custName=custName;
	}
	public Customer(int custId,String custName,Address add)
	{
		this.custId=custId;
		this.custName=custName;
		this.add=add;
	}
}
