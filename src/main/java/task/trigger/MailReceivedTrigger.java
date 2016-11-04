package task.trigger;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import model.task.Trigger;
import task.mail.Mail;

public class MailReceivedTrigger implements Trigger {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(MailReceivedTrigger.class);
	private final String address;
	private final String password;
	private final Mail mailReceiver;

	public MailReceivedTrigger(String address, String password) {
		this.address = address;
		this.password = password;
		mailReceiver = new Mail(address, password);
	}

	@Override
	public int getType() {
		return MAIL_RECEIVED;
	}

	@Override
	public boolean test() {
		if (mailReceiver.isConnect()) {
			if (mailReceiver.hasNewMessage())
				return true;
			else
				return false;
		} else {
			logger.info("Can't connect to Receive Mail Address:"+address);
			return false;
		}
	}

	public String getAddress() {
		return address;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public Map<String, Object> getProperties() {
		// password should not be shown
		return new HashMap<String, Object>() {
			private static final long serialVersionUID = 1L;
			{
				put("address", address);
			}
		};
	}

	@Override
	public String toString() {
		return String.format("MailReceivedTrigger{address=%s}", address);
	}
}
