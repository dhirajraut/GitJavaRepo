package Module13.serialization;
import java.io.*;
import java.util.*;
public class SerialMultipleObjects {
	public static void main (String [] args)  {
		int eno = 1001, eno1 = 1002;
	    String ename = "Ramesh", ename1 = "Umesh";
	    int sal = 10000,sal1 = 15000;
	    
	    Employee eobj1 = new Employee (eno,ename,sal);
	    Employee eobj2 = new Employee (eno1,ename1,sal1);
	    try {
	    	FileOutputStream fout=new FileOutputStream ("emp.ser");
	    	ObjectOutputStream oos=new ObjectOutputStream (fout);
	    	System.out.println ("Serializing object....");
	    	HashSet hs = new HashSet();
	    	hs.add(eobj1);
	    	hs.add(eobj2);
	    	oos.writeObject (hs);
	    	System.out.println ("Objects serialized...");
	    	oos.close (); 
	    	fout.close ();
	    }
	    catch (Exception e)  { 
	    	System.out.println ("Error is : "+e); 
	    }
	}
}
