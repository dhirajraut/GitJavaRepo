package Module02.breakcontinue;

public class TestBreakContinue {

	public static void main(String[] args) {
		for (int i = 1; i <= 70; i++) {
				if (i % 7 == 0)
					continue;
					System.out.print (" " + i);
		
				if (i % 40 == 0) {
			System.out.println ("Terminating loop");
			break;
				}
		}

	}

}
