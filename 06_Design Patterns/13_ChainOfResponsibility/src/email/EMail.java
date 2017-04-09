package email;

public class EMail {
	private boolean isSpamMail;
	private boolean isFanMail;
	private boolean isComplaintMail;
	
	public boolean isComplaintMail() {
		return isComplaintMail;
	}
	public void setComplaintMail(boolean isComplaintMail) {
		this.isComplaintMail = isComplaintMail;
	}
	public boolean isFanMail() {
		return isFanMail;
	}
	public void setFanMail(boolean isFanMail) {
		this.isFanMail = isFanMail;
	}
	public boolean isSpamMail() {
		return isSpamMail;
	}
	public void setSpamMail(boolean isSpamMail) {
		this.isSpamMail = isSpamMail;
	}
	
	public EMail(boolean isSpamMail, boolean isFanMail, boolean isComplaintMail) {
		setSpamMail(isSpamMail);
		setFanMail(isFanMail);
		setComplaintMail(isComplaintMail);
	}
}
