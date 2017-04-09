package controls;

public class LightControl extends Control {
	
	private static LightControl control;

	private LightControl() {}
	
	public static LightControl getLightControl() {
		synchronized (LightControl.class) {
			if (null == control) {
				control = new LightControl();
			}
		}
		return control;
	}
}
