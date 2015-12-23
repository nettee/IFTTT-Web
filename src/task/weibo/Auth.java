package task.weibo;

import weibo4j.Oauth;
import weibo4j.http.AccessToken;

public class Auth {
	public static String getCodeUrL(){
		Oauth oauth = new Oauth();
		try {
			return oauth.authorize("code");
		} catch (weibo4j.model.WeiboException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static AccessToken getAccessToken(String code){
		try {
			return Oauth.getAccessTokenByCode(code);
		} catch (weibo4j.model.WeiboException e) {
			e.printStackTrace();
		}
		return null;
	}
}
