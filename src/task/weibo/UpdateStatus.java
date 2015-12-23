package task.weibo;

import weibo4j.Timeline;

public class UpdateStatus {
	public static boolean postStatus(String access_token, String arg_status)
			throws WeiboException {
		String statuses = arg_status;
		Timeline tm = new Timeline(access_token);
		try {
			tm.updateStatus(statuses);
			return true;
		} catch (weibo4j.model.WeiboException e) {
			throw new WeiboException(e.getMessage(), e.getCause());
		}
	}
}
