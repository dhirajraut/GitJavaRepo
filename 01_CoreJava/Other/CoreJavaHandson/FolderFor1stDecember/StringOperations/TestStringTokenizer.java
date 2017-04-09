import java.util.*;

public class TestStringTokenizer
	{		 public static void main(String argv[])
					{	StringTokenizer st = new StringTokenizer("I am fool. You are cleaver! Are you?", " .!?", false);
						System.out.println("Total tokens in the string :"+st.countTokens());
						System.out.println("List of Tokens:");
						while(st.hasMoreTokens())
							{	System.out.println(st.nextToken()); }
						
						// Separating day month and year from the given date
						StringTokenizer st1 = new StringTokenizer("12/06/2006", "/");
						StringTokenizer st2 = new StringTokenizer("12-06-2006", "-");
						while(st1.hasMoreTokens())
							System.out.println(st1.nextToken());
						System.out.println("-----------");
						while(st2.hasMoreTokens())
							System.out.println(st2.nextToken());
						
						// Separating Vehicle number
						StringTokenizer st3 = new StringTokenizer("MH-34/c 1234/02", "-", false);
						System.out.println(st3.nextToken());	//MH
						System.out.println(st3.nextToken("-/"));	//34
						System.out.println(st3.nextToken("/ "));		//c
						System.out.println(st3.nextToken(" "));	//1234/02
					}
	}