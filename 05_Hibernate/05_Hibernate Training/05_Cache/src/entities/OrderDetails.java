package entities;

import java.io.Serializable;


public class OrderDetails implements Serializable{
	private int orderid;
	private int productid;
	private double unitprice;
	private double quantity;
	
	
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
	}
	public double getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(double unitprice) {
		this.unitprice = unitprice;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	
	public String toString (){
		StringBuffer buf = new StringBuffer();
		buf.append("orderid = " + orderid);
		buf.append("\nproductid = " + productid);
		buf.append("\nunitprice = " + unitprice);
		buf.append("\nquantity = " + quantity);
		return buf.toString();
	}
}
