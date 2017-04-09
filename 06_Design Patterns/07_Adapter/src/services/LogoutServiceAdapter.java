package services;

public class LogoutServiceAdapter {
	LogoutService service = null;
	public LogoutServiceAdapter (LogoutService service) {
		this.service = service;
	}
	public void process() {
		service.execute();
	}
}
