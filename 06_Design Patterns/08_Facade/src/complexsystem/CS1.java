package complexsystem;

public class CS1 {
	public static void method1() {
		CS2.method2();
	}
	public static void method2() {
		CS2.method3();
	}
	public static void method3() {
		CS2.method1();
	}
}
