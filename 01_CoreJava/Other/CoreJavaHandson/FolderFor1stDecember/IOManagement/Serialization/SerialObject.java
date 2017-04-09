package Serialization;
import java.io.*;
public class SerialObject
	{	public static void main(String[] args)
			{	Emp eobj1 = new Emp(101, "Ramesh", 1000, 10, 20);
				Emp eobj2 = new Emp(110, "Ganesh", 2000, 30, 40);

				try {	FileOutputStream fout=new FileOutputStream("emp.ser");
						ObjectOutputStream oos=new ObjectOutputStream(fout);
						System.out.println("Trying to serialize object....");
						oos.writeObject(eobj1);
						oos.writeObject(eobj2);
						System.out.println("Object serialized...");
						oos.close(); fout.close();
					}
				catch (IOException e)
					{	System.out.println("Error: "+e);	}
			}
	}

