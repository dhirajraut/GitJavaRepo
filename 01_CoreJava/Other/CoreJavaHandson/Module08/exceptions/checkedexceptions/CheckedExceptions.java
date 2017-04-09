package Module08.exceptions.checkedexceptions;

import java.io.*;

class CheckedException{
	public static void main(String[] args){
		int noOfBytes = 0;
		char[] data = new char[100];
		try{
			File file = new File("noFile.txt");
			FileReader fr = new FileReader(file);
			noOfBytes = fr.read(data);
		}
		catch(IOException ie){
			System.out.println("Exception : "+ie.getMessage()+"\n"+ ie);
		}
	}
}
