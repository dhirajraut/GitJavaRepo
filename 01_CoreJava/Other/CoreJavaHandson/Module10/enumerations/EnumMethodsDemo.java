package Module10.enumerations;
enum Month {
	  January, February, March, April, May, June, July, August, September, October, November, December
	}
	public class EnumMethodsDemo {
		public static void main (String args[]) {
	    			Month m1;
				Month allMonths[] = Month.values ();

	    			for (Month m : allMonths) {
	      			System.out.println ("Value of m : " + m);
	    			}

				m1 = Month.valueOf ("February");
	    			System.out.println ("\nm1's value is : " + m1);
	  	}
	}

