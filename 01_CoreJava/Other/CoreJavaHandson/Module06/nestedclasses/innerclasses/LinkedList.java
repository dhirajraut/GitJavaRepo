package Module06.nestedclasses.innerclasses;

class LinkedListTest {
	private Item top = null;
  	private Item bottom = null;
  	
  	class Item {
    	public int next;
    	public Item (int next) {
      		this.next = next++;
    	}
  	}
  	public void insert (int val) {
    	Item item = new Item (val);
    	top = item;
    	if (bottom == null)
      			bottom = item;
  	}
  	public void show(){
  		System.out.println("Top :"+top);
  	}
  	
}
class LinkedList{
	public static void main(String[] args){
		LinkedListTest ll = new LinkedListTest();
		LinkedListTest.Item li = ll.new Item(100);
		ll.insert(50);
		ll.show();
		
	}
}
