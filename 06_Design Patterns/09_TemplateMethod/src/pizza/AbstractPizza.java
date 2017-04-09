package pizza;

public abstract class AbstractPizza {
	
	public void createPizzaTemplateMethod() {
		createCrust();
		createPizza();
		addTopings();
	}
	
	protected void createCrust() {
		System.out.println("Creating Crust");
	}
	protected abstract void createPizza();
	
	protected void addTopings() {
		System.out.println("Adding Topings");
	}
}
