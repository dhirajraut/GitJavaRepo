package handlers;

import email.EMail;

public class ComplaintHandler implements IEmailHandler {

	public boolean canHandle() {
		return false;
	}

	public boolean handle(EMail email) {
		if (email.isComplaintMail()) {
			System.out.println("Handling in ComplaintHandler");
			return true;
		}
		return false;
	}

	public IEmailHandler getNextHandler() {
		return null;
	}
}
