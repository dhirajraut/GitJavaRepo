package Module10.wrapperclasses;

public class WrapperTest {
		public static void main (String args[]) {
	    			int i=10;
	    			fun (i); //invalid call to the argument
	    			Integer num = new Integer (i); //wrap the i into Integer class
	    			fun (num); //valid call to fun
	 	 }
	  	static void fun (Integer i) {
	    			System.out.println ("The value is : " + i.intValue ());
	  	}
}


