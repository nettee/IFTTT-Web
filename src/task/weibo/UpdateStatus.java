package task.weibo;

import weibo4j.Timeline;

public class UpdateStatus {
	public static boolean postStatus(String access_token, String arg_status){
		String statuses = arg_status;
		Timeline tm = new Timeline(access_token);
		try {
			tm.updateStatus(statuses);
			return true;
		} catch (weibo4j.model.WeiboException e) {
			e.printStackTrace();
		}
		return false;
	}
}
