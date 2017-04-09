package commands;

import controls.Control;
import controls.FanControl;

public class FanOnCommand implements Command {

	public boolean execute() {
		Control control = (Control)FanControl.getFanControl();
		control.setStatus("ON");
		return false;
	}
}
