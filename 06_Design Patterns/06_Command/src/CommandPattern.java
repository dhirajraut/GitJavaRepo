import commands.FanOffCommand;
import commands.FanOnCommand;
import commands.LightOffCommand;
import commands.LightOnCommand;
import controls.FanControl;

public class CommandPattern {
	public static void main(String args[]) {
		RemoteControl remote = new RemoteControl();
		remote.addCommands(new LightOnCommand());
		remote.addCommands(new LightOffCommand());
		remote.addCommands(new FanOnCommand());
		remote.addCommands(new FanOffCommand());
		
		FanOnCommand fanOn = new FanOnCommand();
		fanOn.execute();
		System.out.println(FanControl.getFanControl().getStatus());
	}
}
