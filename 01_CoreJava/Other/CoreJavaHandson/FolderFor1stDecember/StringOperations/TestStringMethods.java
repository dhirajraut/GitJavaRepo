
public class TestStringMethods
	{	
		public static void main(String [] argv)
			{	/*String abc = "A quick brown fox.";
				System.out.println("First Position of "+"A "+abc.indexOf("A")); // 0
				System.out.println("First Position of "+". "+abc.indexOf("."));	// 17
				System.out.println("First Position of "+"qu "+abc.indexOf("qu"));	// 2
				System.out.println("First Position of "+"ox. "+abc.indexOf("ox."));	// 15
				System.out.println("First Position of "+"o "+abc.indexOf("o"));	// 10
				System.out.println("First Position of "+"o after first"+abc.indexOf("o", 10));	//10
				System.out.println("First Position of "+"o after first"+abc.indexOf("o", 11));	//15
				System.out.println("Last Position of "+"o "+abc.lastIndexOf("o"));	//15
				System.out.println("Last Position of "+"o "+abc.lastIndexOf("o", 15));	//15
				System.out.println("Last Position of "+"o "+abc.lastIndexOf("o", 16));	//15
				System.out.println("Last Position of "+"o "+abc.lastIndexOf("o", 14));	//10
				System.out.println("Last Position of "+"ox "+abc.lastIndexOf("ox"));	//15
				System.out.println("Last Position of "+"ro "+abc.lastIndexOf("ro"));	//9
				if (abc.regionMatches(2, "quick", 0, 0))	// For len parameter values as 0,1,2,3,4,5 - String matches.
					System.out.println("Matched");			// For len parameter values 6 and above - Not matched.
				*/
				// Conversion Methods
				String strInt = "234";
				String strFloat = "123.12";
				
				System.out.println("String \""+strInt+"\" to Integer"+Integer.parseInt(strInt));
				System.out.println("String \""+strFloat+"\" to Float"+Float.parseFloat(strFloat));
				
				int i=432;
				float f=12.123f;
				System.out.println("Integer "+i+" to String \" "+String.valueOf(i));
				System.out.println("Integer "+i+" to String \" "+String.valueOf(i));
			}
	}
