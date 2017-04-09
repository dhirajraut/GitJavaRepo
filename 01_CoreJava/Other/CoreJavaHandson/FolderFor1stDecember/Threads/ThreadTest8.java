import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

import packthreadtest8.ThreadCollectFromFile;
import packthreadtest8.Transact;

public class ThreadTest8
	{	public static void main(String [] argv)
			{	Collection hs = Collections.synchronizedCollection(new HashSet());
				//String filePath = "C:\\D. Chandra\\Java\\D.Chandra New\\ClassroomExercises\\src\\Threads\\packthreadtest8";
				String filePath = "bin\\packthreadtest8";
				String fileNm1 = "Gate1.txt";
				String fileNm2 = "Gate2.txt";
				ThreadCollectFromFile t1 = new ThreadCollectFromFile("Thread1", hs, filePath, fileNm1);
				ThreadCollectFromFile t2 = new ThreadCollectFromFile("Thread2", hs, filePath, fileNm2);
				t1.start();
				t2.start();
				try	{
						t1.join();
						t2.join();
					}
				catch(InterruptedException ie)
					{	}

				System.out.println("Consuming the collected data.");

				// Statements to consume collected data
				Iterator i = hs.iterator();
				while (i.hasNext())
					System.out.println((Transact)i.next());
			}
	}
