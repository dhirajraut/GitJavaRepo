package Module02.namedconstants;

import java.util.Scanner;

public class Area {
	static final float PI = 3.1416f;
	public static void main (String [ ] args) {
		float radius;
		float area;
		Scanner sc = new Scanner(System.in);
		radius = sc.nextFloat();
		area = PI*radius * radius ;
		System.out.println ("Area is " + area);
	}
	
}
