package condiments;

import menu.Menu;

public class ThinCrust extends CondimentDecorator {

	public ThinCrust(final Menu menu) {
		super(menu);
	}

	public double getPrice() {
		return menu.getPrice() + CondimentsConstants.THIN_CRUST_PRICE;
	}

}
