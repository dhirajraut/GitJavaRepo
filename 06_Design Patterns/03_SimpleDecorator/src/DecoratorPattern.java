import condiments.ThinCrust;
import menu.FieryChicken;
import menu.Menu;

public class DecoratorPattern {
	public static void main(String args[]) throws Exception {
		Menu menu = new FieryChicken();
		menu = new ThinCrust(menu);
		System.out.println("Total Price = " + menu.getPrice());
	}
}
