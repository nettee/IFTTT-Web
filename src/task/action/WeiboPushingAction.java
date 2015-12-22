package task.action;

import java.util.HashMap;
import java.util.Map;

import model.task.Action;

public class WeiboPushingAction implements Action {

	private static final long serialVersionUID = 1L;

	private final String username;
	private final String content;

	public WeiboPushingAction(String username, String content) {
		this.username = username;
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
				put("username", username);
				put("content", content);
			}
		};
	}

}
