package controls;

public abstract class Control {
	private String status = "OFF";

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
