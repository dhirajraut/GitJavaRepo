package Module02.ifelse;

public class DayOfWeek {

	public static void main(String[] args) {
		int dayOfWeek = Integer.parseInt (args[0]);
		if (dayOfWeek == 0)
				System.out.println ("Sunday");
		else if (dayOfWeek == 1) 
				System.out.println ("Monday");
		else if (dayOfWeek == 2) 
				System.out.println ("Tuesday");
			else if (dayOfWeek == 3) 
				System.out.println ("Wednesday");
			else if (dayOfWeek == 4)
				System.out.println ("Thursday");
			else if (dayOfWeek == 5)
				System.out.println ("Friday");
			else if (dayOfWeek == 6) 
				System.out.println ("Saturday");
	}
}
