package commands;

import controls.Control;
import controls.LightControl;

public class LightOffCommand implements Command {

	public boolean execute() {
		Control control = (Control)LightControl.getLightControl();
		control.setStatus("OFF");
		return false;
	}
}
