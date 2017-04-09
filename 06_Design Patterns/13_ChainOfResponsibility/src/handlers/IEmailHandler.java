package handlers;

import email.EMail;

public interface IEmailHandler {
	public boolean canHandle();
	public boolean handle(EMail email);
	public IEmailHandler getNextHandler();
}
