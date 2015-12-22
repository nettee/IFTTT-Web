package task.weibo;

import weibo4j.Oauth;
import weibo4j.http.AccessToken;

public class Auth {
	public static String getCodeUrL() throws WeiboException {
		Oauth oauth = new Oauth();
		try {
			return oauth.authorize("code");
		} catch (weibo4j.model.WeiboException e) {
			throw new WeiboException(e.getMessage(), e.getCause());
		}
	}

	public static AccessToken getAccessToken(String code) throws WeiboException {
		try {
			return Oauth.getAccessTokenByCode(code);
		} catch (weibo4j.model.WeiboException e) {
			throw new WeiboException(e.getMessage(), e.getCause());
		}
	}
}
