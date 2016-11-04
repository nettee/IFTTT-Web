package task.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public final class Mail_AuthenticatorGenerator {
	/**
	 * �����û��������룬����Authenticator
	 * @param userName
	 * @param password
	 * @return
	 */
	public static Authenticator getAuthenticator(final String userName, final String password) {
		return new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};
	}
}
