package Module14.interthreadcommunication;
class FoodItem {
	String itemName;
	boolean itemServed = false;
	
	
	synchronized String eat (String i) throws InterruptedException {
	
		if (!itemServed)
			wait ();	
	    System.out.println ("\t\tItem eaten : " + i);
	    itemServed = false;
	    notify ();
	    return itemName;
	  }
	  synchronized void serve (String i) throws InterruptedException {
		 
		  if (itemServed) 
	    	wait ();	
	     
		  itemServed = true;
		  System.out.println ("Item served : " + i);
		  notify ();
	  }
	}
class Waiter extends Thread {
	  String itemList [] = { "Wada" , "Samosa" , "Idli" };
	  FoodItem item = new FoodItem ();
	  Waiter (FoodItem item) {
	    this.item=item;
	  }
	  int length = itemList.length;
	  
	  public void run () {
	    /*while (true) {
	      try { 
	        item.serve (itemList [ i++ ] );
	          if (i>4) i=0;
	          Thread.sleep (2000); 
	      } catch (InterruptedException e) { }
	    */
		  for(int k =0;k<itemList.length;k++){
			  try {
				  item.serve(itemList[k]);
			  } 
			  catch (InterruptedException e) {
				  e.printStackTrace();
			  }
		  
		  }
	  }
}

class Eater extends Thread {
	  FoodItem item;
	  Waiter w = new Waiter(item);
	  Eater (FoodItem item) {
	    this.item=item;
	  }
	  public void run () {
	     for(int p=0;p<w.length;p++){
	    	 try {
	    		 item.eat (w.itemList[p]);
	    		 Thread.sleep (2000); 
	    	 }
	         catch (InterruptedException e) {
	         }
	     }
	  }
}

public class InterThreadedCommunication {
	  public static void main (String [] args) {
	    FoodItem item = new FoodItem ();
	    Waiter w = new Waiter (item);
	    Eater f= new Eater (item);
	    w.start ();
	    f.start ();
	  }
	}
