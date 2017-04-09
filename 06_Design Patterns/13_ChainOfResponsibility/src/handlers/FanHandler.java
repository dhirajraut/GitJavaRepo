package handlers;

import email.EMail;

public class FanHandler implements IEmailHandler {

	public boolean canHandle() {
		return false;
	}

	public boolean handle(EMail email) {
		if (email.isFanMail()) {
			System.out.println("Handling in FanHandler");
			return true;
		}
		return false;
	}

	public IEmailHandler getNextHandler() {
		return new ComplaintHandler();
	}

}
