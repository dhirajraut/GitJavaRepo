package Serialization;
import java.io.*;

public class  DeserialObject {
public static void main(String[] args)
	{
	try {
			FileInputStream fin = new FileInputStream("emp.ser");
			ObjectInputStream ois = new ObjectInputStream(fin);
			System.out.println("De-serializing object....");
			Emp x1 = (Emp) ois.readObject();
			Emp x2 = (Emp) ois.readObject();
			System.out.println("Object de-serialized...");
			System.out.println("Printint values\n");
		
			x1.show();
			x2.show();
		}
	
	catch (IOException e)
		{	System.out.println("Error is : "+e);	}
	
	catch (ClassNotFoundException e)
		{	System.out.println("Class does not exist : "+e);	}
	}
}

