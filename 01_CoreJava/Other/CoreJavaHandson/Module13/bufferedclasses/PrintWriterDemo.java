package Module13.bufferedclasses;
import java.io.*;

public class PrintWriterDemo {
	public static void main (String [] args) {
		PrintWriter pw = new PrintWriter (System.out);
		pw.println ("hello");
		pw.println ("Hi");
		pw.flush ();
	}
}




