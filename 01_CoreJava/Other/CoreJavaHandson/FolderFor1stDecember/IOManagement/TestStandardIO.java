import java.io.*;


public class TestStandardIO
	{	public static void main(String[] args) throws IOException
			{	int i;
				char ch;
				String str;
				boolean b;
				float f;
				
				// For Integer
				System.out.print("Integer : ");
				i=MyIOService.getInt();
				System.out.println("Entered value is : "+i);
		
				// For Character
				System.out.print("Character : ");
				ch=MyIOService.getChar();
				System.out.println("Entered value is : "+ch);
				
				// For String
				System.out.print("String : ");
				str=MyIOService.getString();
				System.out.println("Entered value is : "+str);
				
				// For Boolean
				System.out.print("Boolean : ");
				b=MyIOService.getBoolean();
				System.out.println("Entered value is : "+b);
				
				// For Float
				System.out.print("Float : ");
				f=MyIOService.getFloat();
				System.out.println("Entered value is : "+f);
			}
	}
