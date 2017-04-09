package handlers;

import email.EMail;

public class EmaiHandler {
	private IEmailHandler handler = new SpamHandler();

	public IEmailHandler getHandler() {
		return handler;
	}

	public void setHandler(IEmailHandler handler) {
		this.handler = handler;
	}
	
	public void handle(EMail email) {
		while (!handler.handle(email)){
			if (null != handler.getNextHandler())
				setHandler(handler.getNextHandler());
			else {
				System.out.println("No Handler Found.");
				break;
			}
		}
	}
}
