package controls;

public class FanControl extends Control {
	
	private static FanControl control;

	private FanControl() {}
	
	public static FanControl getFanControl() {
		synchronized (FanControl.class) {
			if (null == control) {
				control = new FanControl();
			}
		}
		return control;
	}
}
