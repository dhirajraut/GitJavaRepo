import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.io.Reader;
import java.io.StreamTokenizer;

public class TestStreamTokenizer
	{	
		public static void main(String[] args) throws FileNotFoundException, IOException
			{	//InputStream is = new InputStream("");
				Reader r = new BufferedReader(new FileReader("c:\\sf"));
			   	StreamTokenizer st = new StreamTokenizer(r);
			   	
			   	while(st.nextToken()!=StreamTokenizer.TT_EOF)
			   		System.out.println(st.sval);
			}
	}
