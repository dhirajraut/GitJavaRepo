package Externalization;
import java.io.*;
public class SerialObject {
	public static void main(String[] args) {
	
	EmpE eobj1 = new EmpE(101, "Ramesh", 1000, 10, 20);
	EmpE eobj2 = new EmpE(110, "Ganesh", 2000, 30, 40);

	try {
	FileOutputStream fout=new FileOutputStream("emp.ser");
	ObjectOutputStream oos=new ObjectOutputStream(fout);
	System.out.println("Trying to externalize object....");
	oos.writeObject(eobj1);
	oos.writeObject(eobj2);
	System.out.println("Object externalized...");
	oos.close(); fout.close();
	}catch (IOException e){System.out.println("Error: "+e);
	}
	}
}

