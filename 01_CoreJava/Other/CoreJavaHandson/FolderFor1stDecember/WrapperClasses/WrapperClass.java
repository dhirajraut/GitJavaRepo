

public class WrapperClass
   { public static void main(String [] arg)
		{ 	System.out.println("Wrapper Class Integer...");
			System.out.println("Minimum Value  :"+Integer.MIN_VALUE);
			System.out.println("Maximum Value  :"+Integer.MAX_VALUE);
			System.out.println("Size Value     :"+Integer.SIZE);
			System.out.println("Premitive Type :"+Integer.TYPE);

			// Conversion Functions
				 Integer I=new Integer(50);
				// Integer--> byte, short, int, float, double
			 			System.out.println("(Use of xxxValue() functon)");
						System.out.println("Integer--> int      :"+I.intValue());
						System.out.println("Integer--> byte     :"+I.byteValue());
						System.out.println("Integer--> long     :"+I.longValue());
						System.out.println("Integer-->short     :"+I.shortValue());
						System.out.println("Integer-->float     :"+I.floatValue());
						System.out.println("Integer-->double    :"+I.doubleValue());
	
				// int --> Integer
						int i = 70;	I=new Integer(i);
						System.out.println("(Use of Constructor)");
						System.out.println("int --> Integer     :"+I);
				// Integer --> String
						String str=I.toString();
						System.out.println("(Use of I.toString() Function)");
						System.out.println("Integer -->  String :"+str);
				// String to Integer
						str="100";	I=Integer.decode(str);
						System.out.println("(Use of Integer.decode(String) Function)");
						System.out.println("String --> Integer  :"+str);
		}
   }
