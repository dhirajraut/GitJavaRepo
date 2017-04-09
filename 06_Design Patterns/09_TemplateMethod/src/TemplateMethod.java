import pizza.AbstractPizza;
import pizza.ChickNSpicy;
import pizza.FieryChicken;

public class TemplateMethod {
	public static void main (String args[]) {
		AbstractPizza pizza = new FieryChicken();
		pizza.createPizzaTemplateMethod();

		pizza = new ChickNSpicy();
		pizza.createPizzaTemplateMethod();
	}
}
