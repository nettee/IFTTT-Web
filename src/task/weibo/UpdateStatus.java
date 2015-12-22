package task.weibo;

import weibo4j.Timeline;
import weibo4j.examples.oauth2.Log;
import weibo4j.http.AccessToken;
import weibo4j.model.Status;

public class UpdateStatus {
	public static boolean postStatus(AccessToken arg0, String arg_status)
			throws WeiboException {
		String access_token = arg0.getAccessToken();
		String statuses = arg_status;
		Timeline tm = new Timeline(access_token);
		Status status;
		try {
			status = tm.updateStatus(statuses);
			Log.logInfo(status.toString());
			return true;
		} catch (weibo4j.model.WeiboException e) {
			throw new WeiboException(e.getMessage(), e.getCause());
		}
	}
}
