package handlers;

import email.EMail;

public class SpamHandler implements IEmailHandler {

	public boolean canHandle() {
		return false;
	}

	public boolean handle(EMail email) {
		if (email.isSpamMail()) {
			System.out.println("Handling in SpamHandler");
			return true;
		}
		return false;
	}

	public IEmailHandler getNextHandler() {
		return new FanHandler();
	}
}
