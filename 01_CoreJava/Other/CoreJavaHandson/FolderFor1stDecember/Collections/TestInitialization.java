/*	The intention here to make an array final, to make its elements final.
 * 	The array can be made final so that assigning it any other array instance of 
 * 		same type is not possible.
 * 	How to make array elements final so that once assigned, their values should
 *  	not be changed.
 *  
 *  The initialization of variables are also checked.
 *  Every local variable needs initialization.  While instance fields may not be initialized.
 */
class Initialization
	{	static  final String [] weekNames; // = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
		static {	weekNames = new String[7];
	
					weekNames[0]="Sunday";
					weekNames[1]="Monday";
					weekNames[2]="Tuesday";
					weekNames[3]="Wednesday";
					weekNames[4]="Thursday";
					weekNames[5]="Friday";
					weekNames[6]="Saturday";
				}
	}

public class TestInitialization
	{	static int  x;
		
		public static void main(String [] arg)
			{	for (int i = 0; i<Initialization.weekNames.length; i++)
					System.out.print(Initialization.weekNames[i]+" ");
				Initialization.weekNames[5]= "friday";
				System.out.println("\n");
				for (int i = 0; i<Initialization.weekNames.length; i++)
					System.out.print(Initialization.weekNames[i]+" ");
			}
		
		public static void printX()
			{	int x;
				System.out.println("x="+TestInitialization.x);
			}
	}
