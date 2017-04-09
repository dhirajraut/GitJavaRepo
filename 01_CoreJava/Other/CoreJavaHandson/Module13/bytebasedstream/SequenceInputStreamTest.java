package Module13.bytebasedstream;

import java.io.*;

public class  SequenceInputStreamTest {
	  public static void main (String [] args) {
	    try {
	      FileInputStream f1 = new FileInputStream ("File1.txt");
	      FileInputStream f2 = new FileInputStream ("NewFile.txt");
	      SequenceInputStream seq = new SequenceInputStream (f1,f2);
	      int b = seq.read (); 
	      while (b != -1) {
				 System.out.print ( (char) b);
				  b = seq.read ();
		  } 
	      seq.close (); f1.close (); f2.close ();
	    } catch (IOException e) { System.out.println ("Error in IO is : " + e);     }
	  }
	}



