package Module13.serialization;

import java.io.*;

class Employee implements Serializable {
	int eno;
	String ename;
	int sal;
	  	
	public Employee (int eno, String ename, int sal) {
		this.eno=eno;
		this.ename=ename;
		this.sal=sal;
	}
	public void show () {
		System.out.println ("Emp number   : " + eno);
		System.out.println ("Emp name      : " + ename);
	    System.out.println ("Emp sal          : " + sal);
  	}		
}
class Serialization {
	public static void main (String [] args) {
		Employee e = new Employee(101,"jack",10000);
		try {
			FileOutputStream fout=new FileOutputStream ("employee.ser");
			ObjectOutputStream oos=new ObjectOutputStream (fout);
			System.out.println ("Trying to serialize object....");
			oos.writeObject (e);
			System.out.println ("Object serialized...");
			oos.close ();
			fout.close ();
		}
		catch (IOException ioe) { 
			System.out.println ("Error: "+e); 
		}
	}
}

