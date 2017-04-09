package condiments;

import menu.Menu;

public class StuffedCrust extends CondimentDecorator {

	public StuffedCrust(final Menu menu) {
		super(menu);
	}

	public double getPrice() {
		return menu.getPrice() + CondimentsConstants.STUFFED_CRUST_PRICE;
	}

}
