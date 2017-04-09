import java.util.ArrayList;
import java.util.List;

import commands.Command;

public class RemoteControl {
	private List<Command> commands = new ArrayList <Command>();

	public List<Command> getCommands() {
		return commands;
	}

	public void setCommands(List<Command> controls) {
		this.commands = controls;
	}
	
	public void addCommands(Command command) {
		commands.add(command);
	}
	
}
