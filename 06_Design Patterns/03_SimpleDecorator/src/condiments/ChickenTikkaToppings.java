package condiments;

import menu.Menu;

public class ChickenTikkaToppings extends CondimentDecorator {

	public ChickenTikkaToppings(final Menu menu) {
		super(menu);
	}

	public double getPrice() {
		return menu.getPrice() + CondimentsConstants.CHICKEN_TIKKA_TOPINGS_PRICE;
	}

}
