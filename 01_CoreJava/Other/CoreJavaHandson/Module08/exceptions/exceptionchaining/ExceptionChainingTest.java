package Module08.exceptions.exceptionchaining;

import java.io.*;

public class ExceptionChainingTest {
	public static void main(String[] args){
		try{
			FileReader fr = new FileReader("Pragati.txt");
			int i = fr.read();
		}
		catch(IOException ioe){
			NullPointerException npe = new NullPointerException("caught");
			npe.initCause(ioe);
			throw npe;
		}
	}
}
