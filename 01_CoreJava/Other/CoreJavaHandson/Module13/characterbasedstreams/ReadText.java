package Module13.characterbasedstreams;
import java.io.*;

public class ReadText {
	public static void main (String [] args) {
			int i=0;
			try {
				FileReader fr = new FileReader ("File1.txt");
				FileWriter fw= new  FileWriter("NewFile.txt");

				while ((i=fr.read())!=-1) {
					fw.write(i); 

				}
				fr.close();
				fw.close();
			} catch (IOException e) { 
				e.printStackTrace();
			}
	}
}


