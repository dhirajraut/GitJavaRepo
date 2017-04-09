package commands;

import controls.Control;
import controls.LightControl;

public class LightOnCommand implements Command {

	public boolean execute() {
		Control control = (Control)LightControl.getLightControl();
		control.setStatus("ON");
		return false;
	}
}
