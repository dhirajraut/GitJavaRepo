package Module13.bufferedclasses;

import java.io.*;

public class LineReader {
	public static void main(String[] args){
		try{
			FileReader fr = new FileReader("File1.txt");
			BufferedReader br = new BufferedReader(fr);
		
			String s = br.readLine();
			
			while(s!= null){
				System.out.println(s);
				s = br.readLine();
			}
		}
		catch(IOException ioe){
			ioe.printStackTrace();
		}
		
	}
}
