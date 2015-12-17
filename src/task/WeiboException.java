package task;

@SuppressWarnings("serial")
public class WeiboException extends RuntimeException {

	public WeiboException() {
		super();
	}

	public WeiboException(String message) {
		super(message);
	}

	public WeiboException(Throwable cause) {
		super(cause);
	}

	public WeiboException(String message, Throwable cause) {
		super(message, cause);
	}

}
