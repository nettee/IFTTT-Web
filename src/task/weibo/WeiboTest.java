package task.weibo;

public class WeiboTest {
	public static void main(String[] args) {
		String access_token="2.004gqyLE0z8m3q9f27e5180bNED43B";
		System.out.println(GetNewStatus.getNewStatus(access_token));
		System.out.println(GetNewStatus.getNewStatus_Date(access_token));
		System.out.println(UpdateStatus.postStatus(access_token,"hello"));
	}
}
