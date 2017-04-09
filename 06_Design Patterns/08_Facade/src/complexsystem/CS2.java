package complexsystem;

public class CS2 {
	public static void method1() {
		CS3.method2();
	}
	public static void method2() {
		CS3.method3();
	}
	public static void method3() {
		CS3.method1();
	}
}
