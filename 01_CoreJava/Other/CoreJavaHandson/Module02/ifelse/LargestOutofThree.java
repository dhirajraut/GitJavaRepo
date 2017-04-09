package Module02.ifelse;

public class LargestOutofThree {

	public static void main(String[] args) {
		int a = 25, b = 15, c = 10, largest;
	 	if (a > b)	{
	      		if (a > c)
	      			largest = a;
	      		else
	      			largest = c;
	  	}
	  	else {
				if (b > c)
	        		largest = b;
	  		else
	        		largest = c; 
			}
	  	System.out.println ("Largest : " + largest);
	}
}
