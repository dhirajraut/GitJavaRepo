package Module05.interfaces;

class GraphicTest {
	 public static void main (String [] args) {
   			Graphic g1 = new Circle (10);
   			Graphic g2 = new Rectangle (5,4);
   			System.out.println ("Area of circle is " + g1.area ());
   			System.out.println ("Periphery of circle is " + g1.periphery ());
   			System.out.println ("Area of rectangle is " + g2.area ());
   			System.out.println ("Periphery of rectangle is " + g2.periphery ());
 	}
}
