package weibo_pack;

import weibo4j.Timeline;
import weibo4j.examples.oauth2.Log;
import weibo4j.http.AccessToken;
import weibo4j.model.Status;
import weibo4j.model.WeiboException;

public class UpdateStatus {
	public static boolean postStatus(AccessToken arg0, String arg_status) {
		String access_token = arg0.getAccessToken();
		String statuses = arg_status;
		Timeline tm = new Timeline(access_token);
		try {
			Status status = tm.updateStatus(statuses);
			Log.logInfo(status.toString());
			return true;
		} catch (WeiboException e) {
			e.printStackTrace();
		}
		return false;
	}
}
