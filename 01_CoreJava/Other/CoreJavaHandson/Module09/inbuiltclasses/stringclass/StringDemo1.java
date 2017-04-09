package Module09.inbuiltclasses.stringclass;

class StringDemo1 {
	public static void main(String[] args) {
				String str = "abc";
				String str1 = "abc";
				String str2 = new String("abc");
				String str3 = "xyz";
				//immutability
				System.out.println(str.concat("def"));
				System.out.println(str);
			
				// using == operator
				System.out.println(" str == str1 : " + (str == str1));
				System.out.println("str == str2 : " + (str == str2));
				System.out.println(" str == str3 : " + (str == str3)); 

				// using equals() method
				System.out.println(" str.equals(str1) : " +(str.equals(str1)));
				System.out.println(" str.equals(str2) : " +(str.equals(str2))); 
				System.out.println(" str.equals(str2) : " +(str.equals(str3)));
			}
	}
