package Module02.breakcontinue;

public class LabelledBreak {

	public static void main(String[] args) {
		outer:
			for (int i = 1; i <= 10; i++) {
  				for (int j = 1; j <= 5; j++) {
    			System.out.print ("\t" + (i * j));
        		if ( (i * j) == 18){
			      	break outer;
				}
  			}
  			System.out.println ();
	}
}
}
