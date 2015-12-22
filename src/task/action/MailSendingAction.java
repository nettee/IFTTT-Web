package task.action;

import java.util.HashMap;
import java.util.Map;

import model.task.Action;

public class MailSendingAction implements Action {

	private static final long serialVersionUID = 1L;

	private final String address;
	private final String subject;
	private final String content;

	public MailSendingAction(String address, String subject, String content) {
		this.address = address;
		this.subject = subject;
		this.content = content;
	}

	@Override
	public int getType() {
		return MAIL_SENDING;
	}

	@Override
	public void perform() {
		// TODO Auto-generated method stub
	}

	@Override
	public Map<String, Object> getProperties() {
		return new HashMap<String, Object>() {
			private static final long serialVersionUID = 1L;
			{
				put("address", address);
				put("subject", subject);
				put("content", content);
			}
		};
	}

	@Override
	public String toString() {
		return String.format(
				"MailSendingAction{address=%s, subject=%s, content=%s}",
				address, subject, content);
	}

}
