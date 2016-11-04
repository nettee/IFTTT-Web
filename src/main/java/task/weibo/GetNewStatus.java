package task.weibo;

import java.util.Date;
import java.util.List;

import weibo4j.Timeline;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;

//getUserTimelineByName() 接口升级后：uid与screen_name只能为当前授权用户；
public class GetNewStatus {
	public static String getNewStatus(String access_token) {
		Timeline tm = new Timeline(access_token);
		try {
			StatusWapper statuslist = tm.getUserTimeline();
			List<Status> status = statuslist.getStatuses();
			return status.get(0).getText();
		} catch (weibo4j.model.WeiboException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static Date getNewStatus_Date(String access_token) {
		Timeline tm = new Timeline(access_token);
		try {
			StatusWapper statuslist = tm.getUserTimeline();
			List<Status> status = statuslist.getStatuses();
			return status.get(0).getCreatedAt();
		} catch (weibo4j.model.WeiboException e) {
			e.printStackTrace();
		}
		return null;
	}
}
