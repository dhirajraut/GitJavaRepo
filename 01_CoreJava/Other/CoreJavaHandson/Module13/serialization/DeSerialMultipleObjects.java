package Module13.serialization;
import java.io.*;
import java.util.*;

public class DeSerialMultipleObjects {
	public static void main (String [] args) {
		try {
			FileInputStream fin = new FileInputStream ("emp.ser");
		    ObjectInputStream ois = new ObjectInputStream (fin);
		    System.out.println ("De-serializing objects....");
		    Vector v = (Vector) ois.readObject ();
		    Enumeration e = v.elements ();
		    System.out.println ("Object de-serialized...");
		    while (e.hasMoreElements ()) {
		    	Employee x = (Employee) e.nextElement ();
		        x.show ();
		    }
			 
		}
		catch (IOException ioe) { 
			System.out.println (ioe);
		}
		catch (ClassNotFoundException cfe) {
			System.out.println (cfe);
		}
	 }
}




