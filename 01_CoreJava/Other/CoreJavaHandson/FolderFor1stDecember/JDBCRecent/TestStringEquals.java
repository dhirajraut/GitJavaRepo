
public class TestStringEquals
	{	public static void main(String[] argv)
			{	String s1 = "abc";
				String s2 = s1;
				String s3 = "abc";
				String s4 = "pqr";
				String s5 = new String("abc");
				
				System.out.println("s1="+s1+" Hashcode: "+s1.hashCode());
				System.out.println("s2="+s2+" Hashcode: "+s2.hashCode());
				System.out.println("s3="+s3+" Hashcode: "+s3.hashCode());
				System.out.println("s4="+s4+" Hashcode: "+s4.hashCode());
				System.out.println("s5="+s5+" Hashcode: "+s5.hashCode());
				
				if (s1==s2)
					{	System.out.print("s1==s2");	}
				else
					{	System.out.print("s1!=s2");	}
				if (s1.equals(s2))
					{	System.out.println("\ts1.equlas(s2)");	}
				else
					{	System.out.println("\t!s1.equlas(s2)");	}
				
				if (s1==s3)
					{	System.out.print("s1==s3");	}
				else
					{	System.out.print("s1!=s3");	}
				if (s1.equals(s3))
					{	System.out.println("\ts1.equlas(s3)");	}
				else
					{	System.out.println("\t!s1.equlas(s3)");	}
				
				if (s1==s4)
					{	System.out.print("s1==s4");	}
				else
					{	System.out.print("s1!=s4");	}
				if (s1.equals(s4))
					{	System.out.println("\ts1.equlas(s4)");	}
				else
					{	System.out.println("\t!s1.equlas(s4)");	}
				
				if (s1==s5)
					{	System.out.print("s1==s5");	}
				else
					{	System.out.print("s1!=s5");	}
				if (s1.equals(s5))
					{	System.out.println("\ts1.equlas(s5)");	}
				else
					{	System.out.println("\t!s1.equlas(s5)");	}
			}
	}
