package Module13.ioservices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyIOService{
	static BufferedReader in ;
	static String currentline;
		
	public static int getInt () throws  IOException,NumberFormatException{
		in = new BufferedReader (new InputStreamReader (System.in));
		currentline = in.readLine ();
					 
		int n=Integer.parseInt(currentline);
		return(n);
	}
	public static char getChar () throws IOException{
		in = new BufferedReader (new InputStreamReader (System.in));
		currentline = in.readLine ();
		return currentline.charAt(0);
	}
	public static String getString () throws IOException{
		in = new BufferedReader (new InputStreamReader (System.in));
		currentline = in.readLine ();
		return currentline;
	}
	public static boolean getBoolean () throws IOException{
		in = new BufferedReader (new InputStreamReader (System.in));
		currentline = in.readLine ();
		boolean bval = Boolean.parseBoolean(currentline);
		return bval;
	}
	public static float getFloat () throws  IOException	{
		in = new BufferedReader (new InputStreamReader (System.in));
		currentline = in.readLine ();
		float fval = Float.parseFloat(currentline);
		return fval;
	}
	public static double getDouble() throws IOException{
		in = new BufferedReader (new InputStreamReader (System.in));
		currentline = in.readLine ();
		double dval = Double.parseDouble(currentline);
		return dval;
	}
}