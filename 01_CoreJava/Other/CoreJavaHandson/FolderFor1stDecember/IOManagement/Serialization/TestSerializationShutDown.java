// TestSerializationShutDown.java
import java.io.*;
public class TestSerializationShutDown
	{	public static void main(String[] args) throws ClassNotFoundException, IOException
			{	// Create an object.  For serializing, it must be implemented from interface 'Serializable'
				TestSerializationTimer t= new TestSerializationTimer("Timer A", 0l);
				t.begin();	// Fire a thread 't'.
				try	{
						Thread.sleep(2000);	// Sleep this program to allow thread 't' to increment timer
					}
				catch (InterruptedException e)
					{}
				t.end();	// Terminate a thread 't'.
				System.out.println("Data being Serialized..");
				System.out.println("Timer name :"+t.getTimerName());
				System.out.println("Timer Counter :"+t.getCounter());
				
				// ObjectOutputStream is designed to handle serialization process.
				// This stream always has a destination as other streams.
				// Here, 'FileOutputStream' is a destination for 'ObjectOutputStream'
				//while destination for FileOutputStream is a disk file "Objects.tmp".
				FileOutputStream fos = new FileOutputStream("Objects.tmp");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(t);	// It is Serializing an object 't'. Except Transient fields
									// all other fields are serialized.
				oos.flush();		// Flushing out buffer
				fos.close();
			}
}
