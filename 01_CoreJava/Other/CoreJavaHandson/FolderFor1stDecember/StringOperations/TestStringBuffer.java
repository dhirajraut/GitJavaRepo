import java.lang.*;
/*
* Allocates minimum 16 bytes of memory space though empty string is given
* If more capacity is ensured than 16, capacity increses by 18
* If smaller capacity is ensured than actually, string buffer is having, no chage appears.
* length() gives number of characters present in a StrinBuffer.This number can be
  increased/decreased by setting setLength().  
* The capacity can be trimed to smaller size.  It is  opposite to ensureCapacity()
* If more length is given than number of characters present in the string, garbaj chara
   cters are seen to fill rest of the characters to make a string of equal to length.
* The reverse() really reverses a string present in the StringBuffer
*/

public class TestStringBuffer
	{	static StringBuffer sb=new StringBuffer();
		static void printParameters(String str)
			{	System.out.println(str);
				System.out.println("\tCapacity :"+sb.capacity());
				System.out.println("\tLength   :"+sb.length());
				System.out.println("\tString:"+sb);
			}
		public static void main(String[] args)
			{	printParameters("1. For empty String Buffer...");
				
				sb.append("a");	
				printParameters("2. For String Buffer with 1 chara(append('a'))...");
				
				sb.ensureCapacity(36);	// Ensures higher capacity
				printParameters("3. For String Buffer after ensure capacity 36(ensureCapacity(36))...");
				
				sb.setLength(20);
				printParameters("4. For String Buffer after setting length 20(setLength(20))...");
				
				sb.setLength(10);
				printParameters("5. For String Buffer setting length 10 to smaller size(setLength(10))...");
				
				//sb.trimToSize();	// Trims capacity to seted length.// The method is given in Java 1.5
				printParameters("6. For String Buffer after trimed to size(trimToSize())...");
				
				sb.append("bcdefghijklmnopqrstuvwxyz");
				printParameters("7. For String Buffer after storing larger string(append('bcdefghijklmnopqrstuvwxyz'))...");
				
				sb.setLength(26);
				printParameters("8. For String Buffer after setting size equal to stored string(setLength(26))...");
				
				sb.setLength(20);
				printParameters("9. For String Buffer after setting size smaller to stored string(setLength(20))...");
				
				sb.setLength(1);
				sb.append("bcdefghijklmnopqrstuvwxyz");
				sb.setLength(26);
				printParameters("10.For String Buffer trying to reduce descripancy(setLength(1))(append('bcdefghijklmnopqrstuvwxyz'))(setLength(26))");
				
				sb=sb.reverse();
				printParameters("11.For String Buffer trying to reverse(reverse())");
				
				sb.ensureCapacity(10);
				printParameters("12.For String Buffer reduced to smaller capacity(ensureCapacity(10))");
			}
}
