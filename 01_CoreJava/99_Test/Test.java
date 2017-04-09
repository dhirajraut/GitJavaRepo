
public class Test {
	public static void main(String args[]) {
		System.out.println(method1());
	}
	
	public static String method1() {
		try {
			int i = 0/0;
			System.out.println("In try");
			return "Try";
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("In catch ");
			return "catch";
		}
		finally {
			System.out.println("In Finally");
			return "Finally";
		}
	}
}
