import handlers.EmaiHandler;
import email.EMail;

public class ChainOfResponsibility {
	public static void main(String args[]) {
		EMail email = new EMail(false, false, false);

		email.setSpamMail(true);
		EmaiHandler emailHandler = new EmaiHandler();
		emailHandler.handle(email);

		email = new EMail(false, false, false);
		email.setFanMail(true);
		emailHandler = new EmaiHandler();
		emailHandler.handle(email);

		email = new EMail(false, false, false);
		email.setComplaintMail(true);
		emailHandler = new EmaiHandler();
		emailHandler.handle(email);

		email = new EMail(false, false, false);
		emailHandler = new EmaiHandler();
		emailHandler.handle(email);
	}
}
