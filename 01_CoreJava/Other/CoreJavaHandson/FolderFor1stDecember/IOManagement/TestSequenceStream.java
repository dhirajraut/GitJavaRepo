
import java.io.*;

public class  TestSequenceStream {
public static void main(String[] args) {
try {
	FileInputStream f1 = new FileInputStream("abc.txt");
	FileInputStream f2 = new FileInputStream("xyz.txt");
	SequenceInputStream seq = new SequenceInputStream(f1,f2);
			int b = 0; b = seq.read(); 
			while(b!=-1)	{
				System.out.print((char)b);
				b = seq.read(); 
			} 
			seq.close();	f1.close(); f2.close();
		}catch (FileNotFoundException e)	{
			System.out.println("Unable to open file");
		}catch(IOException e) {
			System.out.println("Error in IO is : "+e);
		}
	}
}

