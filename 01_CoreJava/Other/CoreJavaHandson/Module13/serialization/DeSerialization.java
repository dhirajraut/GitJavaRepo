package Module13.serialization;
import java.io.*;

public class DeSerialization {
	public static void main (String [] args) {
		try {
			FileInputStream fin = new FileInputStream ("employee.ser");
	      	ObjectInputStream ois = new ObjectInputStream (fin);
	      	System.out.println ("De-serializing object....");
	     	Employee x = (Employee) ois.readObject ();
	     	System.out.println ("Object de-serialized...");
	     	System.out.println ("Printint values\n");
	     	x.show ();
		}
		catch (IOException ioe) {
	    	System.out.println ("Error is : " + ioe);
	   	}
		catch (ClassNotFoundException e) {
	    	 System.out.println ("Class does not exist : " + e);
		}
	}
}
