package task.mail;

import java.io.Serializable;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class Mail implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2672111673108677695L;
	Properties props;
	String userName, password;
	Mail_Host mail_Host;
	int mail_count;
	public boolean connect = false;

	public boolean isConnect() {
		return connect;
	}

	public Mail(String arg_name, String arg_password) {
		String mail_end = arg_name.split("@")[1];
		userName = arg_name;
		password = arg_password;
		mail_count = 0;
		if (mail_end.equals("qq.com")) {
			mail_Host = Mail_Host.TENCENT;
			props = mail_Host.getProperties();
		} else if (mail_end.equals("163.com")) {
			mail_Host = Mail_Host.NETEASE;
			props = mail_Host.getProperties();
		} else if (mail_end.equals("yeah.net")) {
			mail_Host = Mail_Host.NETEASE2;
			props = mail_Host.getProperties();
		} else {
			throw new MailException("UnSupported Mail Type");
		}
		Folder folder = fetchInbox(props,
				Mail_AuthenticatorGenerator
						.getAuthenticator(userName, password));
		try {
			if (folder.exists()) {
				connect = true;
				mail_count = folder.getMessageCount();
			} else
				connect = false;
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (MailException e) {
			e.printStackTrace();
		}
	}

	private Folder fetchInbox(Properties props, Authenticator authenticator)
			throws MailException {
		return fetchInbox(props, authenticator, null);
	}

	private Folder fetchInbox(Properties props, Authenticator authenticator,
			String protocol) throws MailException {
		Session session = Session.getDefaultInstance(props, authenticator);
		Store store = null;
		Folder folder = null;
		try {
			store = protocol == null || protocol.trim().length() == 0 ? session
					.getStore() : session.getStore(protocol);
			store.connect();
			folder = store.getFolder("INBOX");
			folder.open(Folder.READ_ONLY);
		} catch (NoSuchProviderException e) {
			throw new MailException("No such Provider");
		} catch (MessagingException e) {
			throw new MailException(e.getMessage(), e.getCause());
		}
		return folder;
	}

	public boolean hasNewMessage() throws MailException {
		int newAllMessage = 0;
		try {
			Folder folder = fetchInbox(props,
					Mail_AuthenticatorGenerator.getAuthenticator(userName,
							password));
			newAllMessage = folder.getNewMessageCount();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (MailException e) {
			e.printStackTrace();
		}

		if (newAllMessage > mail_count) {
			mail_count = newAllMessage;
			return true;
		} else {
			mail_count = newAllMessage;
			return false;
		}
	}
	public boolean sendMessage(String address, String subject, String content) {
		Session session = Session.getDefaultInstance(props,
				Mail_AuthenticatorGenerator
						.getAuthenticator(userName, password));
		MimeMessage message = new MimeMessage(session);
		try {
			InternetAddress from;
			from = new InternetAddress(userName);
			message.setFrom(from);
			InternetAddress to = new InternetAddress(address);
			message.setSubject("[IFTTT]" + subject);
			message.setText(content);
			message.setRecipient(RecipientType.TO, to);
			Transport transport = session.getTransport("smtps");
			transport.connect(props.getProperty("mail.smtp.host"), 465, userName, password);
			
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return false;
	}
}