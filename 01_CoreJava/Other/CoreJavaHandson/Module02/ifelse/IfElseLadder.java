package Module02.ifelse;

public class IfElseLadder {

	public static void main(String[] args) {
		long net = 550000L;
		int incomeTaxRate;
		if ( 0<net && net <= 150000L) 
			incomeTaxRate = 0;
		else {
			 if (150000L<net && net <= 300000L)
				incomeTaxRate = 10;
			else {
				  if (300000L<net && net <= 500000L)
		            		incomeTaxRate = 20;
				  else
		         		incomeTaxRate = 30;
			 }
		}	
		System.out.println ("Income tax Rate : " + incomeTaxRate);
	}
	}
