package task.trigger;

import java.util.HashMap;
import java.util.Map;

import model.task.Trigger;

public class MailReceivedTrigger implements Trigger {

	private static final long serialVersionUID = 1L;

	private final String address;
	private final String password;

	public MailReceivedTrigger(String address, String password) {
		this.address = address;
		this.password = password;
	}

	@Override
	public boolean test() {
		// TODO
		return false;
	}

	public String getAddress() {
		return address;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return String.format("MailReceivedTrigger(address: %s, password: %s)", address, password);
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

}