package task.weibo;

import weibo4j.Oauth;
import weibo4j.http.AccessToken;
import weibo4j.model.WeiboException;
import weibo4j.util.BareBonesBrowserLaunch;

public class Auth {
	public static void getCode() {
		Oauth oauth = new Oauth();
		try {
			BareBonesBrowserLaunch.openURL(oauth.authorize("code"));
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}
	
	public static AccessToken getAccessToken(String code){
		try {
			return Oauth.getAccessTokenByCode(code);
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
