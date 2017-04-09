package condiments;

import menu.Menu;

public abstract class CondimentDecorator extends Menu {
	protected Menu menu;
	public CondimentDecorator(Menu menu) {
		this.menu = menu;
	}
}
