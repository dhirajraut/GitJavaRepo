package packbill;

public class ItemDetails {
	private int itemNo;
	private int itemQty;
	public ItemDetails(int itemNo, int itemQty) {
		super();
		this.itemNo = itemNo;
		this.itemQty = itemQty;
	}
	public int getItemNo() {
		return itemNo;
	}
	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}
	public int getItemQty() {
		return itemQty;
	}
	public void setItemQty(int itemQty) {
		this.itemQty = itemQty;
	}
	
	public String toString(){
		return "Item No:"+ itemNo+" Item Qty:"+ itemQty;
	}
}
