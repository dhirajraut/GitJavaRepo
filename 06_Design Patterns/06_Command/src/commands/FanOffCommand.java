package commands;

import controls.Control;
import controls.FanControl;

public class FanOffCommand implements Command {

	public boolean execute() {
		Control control = (Control)FanControl.getFanControl();
		control.setStatus("OFF");
		return false;
	}
}
