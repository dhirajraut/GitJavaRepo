package Module10.enumerations;

enum Months {
January, February, March, April, May, June, July, August, September, October, November,
 December
}
public class MonthsEnumDemo {
	public static void main (String args[]) {
    			Months m1;
   			m1 = Months.April;    System.out.println ("Value of m1 : " + m1);
    			m1 = Months.November;    System.out.println ("Value of m1 : " + m1);
    			if (m1==Months.November) {
      			System.out.println ("m1 contains November");
    			}
    			switch(m1) {
    		           case November :  System.out.println ("Its a winter month"); break;
      		   case May: System.out.println ("Its a summer month"); break;
                   case July: System.out.println ("Its a rainy month"); break;
    			}   
  	}
}
