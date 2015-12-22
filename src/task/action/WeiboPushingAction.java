package task.action;

import java.util.HashMap;
import java.util.Map;

import model.task.Action;

public class WeiboPushingAction implements Action {

	private static final long serialVersionUID = 1L;

	private final String user;
	private final String content;

	public WeiboPushingAction(String user, String content) {
		this.user = user;
		this.content = content;
	}

	@Override
	public int getType() {
		return WEIBO_PUSHING;
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
				put("user", user);
				put("content", content);
			}
		};
	}

	@Override
	public String toString() {
		return String.format("WeiboPushingAction{user=%s, content=%s",
				user, content);
	}

}
