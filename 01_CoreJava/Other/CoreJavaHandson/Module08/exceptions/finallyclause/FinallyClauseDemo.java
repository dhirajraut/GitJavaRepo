package Module08.exceptions.finallyclause;

import java.io.*;
class FinallyClauseDemo {
	public static void main (String [] args) throws IOException {
    			InputStream in = null;
    			try {
      				in = new FileInputStream (args [0]);
      				int total = 0;
     				while (in.read () != -1)
					 total ++;
      					System.out.println (total + " bytes.");
    			} 
			catch (FileNotFoundException e) { 
      				System.out.println ("File not found.");
    			 }
			  finally {
      				if (in != null) in.close ();
    			}
  	}
}

