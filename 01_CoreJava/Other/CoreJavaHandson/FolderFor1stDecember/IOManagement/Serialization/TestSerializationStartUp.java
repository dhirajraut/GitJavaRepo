// TestSerializationStartUp.java
/* Deserializing the object 't' which was serialized in ShutDown program.
 * The counter and the name are two fields which can be deserialized.
 * Others being transient fields(Declared in class of 't'), were neither serialized so
 * are not deserialized here.
 */
import java.io.*;
public class TestSerializationStartUp
	{	public static void main(String[] args) throws ClassNotFoundException, IOException
			{	// ObjectInputStream is designed to handle deserialization process.
				// This stream always has a source as other streams.
				// Here, 'FileInputStream' is a source for 'ObjectInputStream'
				//while source for FileInputStream is a disk file "Objects.tmp".
		
				FileInputStream fis = new FileInputStream("Objects.tmp");
				ObjectInputStream ois = new ObjectInputStream(fis);
				// Now, read an object from "Objects.tmp".  Basically it is 'Object' type
				// so cast it into its own type and assign its reference to reference 't'.
				// Remember, only non-transient fields are deserialized.
				TestSerializationTimer t = (TestSerializationTimer) ois.readObject();
				fis.close();
				System.out.println("Timer name :"+t.getTimerName());
				System.out.println("Timer name :"+t.getCounter());
				t.begin();	// Continue thread with deserialized initial values.
				for(int i = 0; i<10; i++)
					{	System.out.println("Counter in StartUp"+t.getCounter());
						try	{
								Thread.sleep(100);
							}
						catch(InterruptedException e)
							{}
					}
				t.end();
			}
	}
