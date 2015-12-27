package task.action;

import java.util.HashMap;
import java.util.Map;

import model.task.Action;
import task.weibo.Auth;
import task.weibo.UpdateStatus;

public class WeiboPostingAction implements Action {

	private static final long serialVersionUID = 1L;
	
	private final String user;
	private final String code;
	private final String content;

	public WeiboPostingAction(String user, String code, String content) {
		this.user = user;
		this.code = code;
		this.content = content;
	}

	@Override
	public int getType() {
		return WEIBO_PUSHING;
	}

	@Override
	public void perform() {
		String token = Auth.getAccessToken(code).getAccessToken();
		UpdateStatus.postStatus(token, content);
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
