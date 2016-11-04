package task.mail;

@SuppressWarnings("serial")
public class MailException extends RuntimeException {

	public MailException() {
		super();
	}

	public MailException(String message) {
		super(message);
	}

	public MailException(Throwable cause) {
		super(cause);
	}

	public MailException(String message, Throwable cause) {
		super(message, cause);
	}

}
