/*package task.mail;

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

public class Mail {
	int current_Count;
	Properties props;
	String userName, password;
	Mail_Host mail_Host;

	public boolean isConnect() {
		return connect;
	}

	public boolean connect = false;

	public Mail(String arg_name, String arg_password) {
		String mail_end = arg_name.split("@")[1];

		switch (mail_end) {
		case "qq.com":
			mail_Host = Mail_Host.TENCENT;
			props = mail_Host.getProperties();
			break;
		case "163.com":
			mail_Host = Mail_Host.NETEASE;
			props = mail_Host.getProperties();
			break;
		case "yeah.net":
			mail_Host = Mail_Host.NETEASE2;
			props = mail_Host.getProperties();
			break;
		default:

		}
		current_Count = 0;
		userName = arg_name;
		password = arg_password;
		int newAllMessage = 0;
		Folder folder = fetchInbox(props, Mail_AuthenticatorGenerator.getAuthenticator(userName, password));
		try {
			if (folder.exists())
				connect = true;
			else
				connect = false;
			newAllMessage = folder.getMessageCount();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		current_Count = newAllMessage;
	}

	public Mail(String arg_name, String arg_password, Log_Field arg_field) {
		String[] address = arg_name.split("@");
		String mail_end = " ";
		if (address.length == 2)
			mail_end = arg_name.split("@")[1];
		log_Field = arg_field;
		switch (mail_end) {
		case "qq.com":
			log_Field.add(arg_name + " 邮箱类型为：qq邮箱");
			mail_Host = Mail_Host.TENCENT;
			props = mail_Host.getProperties();
			break;
		case "163.com":
			log_Field.add(arg_name + " 邮箱类型为：网易163邮箱");
			mail_Host = Mail_Host.NETEASE;
			props = mail_Host.getProperties();
			break;
		case "yeah.net":
			log_Field.add(arg_name + " 邮箱类型为：网易yeah.net邮箱");
			mail_Host = Mail_Host.NETEASE2;
			props = mail_Host.getProperties();
			break;
		default:
			log_Field.add("不支持的邮箱类型");
			return;
		}
		current_Count = 0;
		userName = arg_name;
		password = arg_password;
		int newAllMessage = 0;
		Folder folder = fetchInbox(props, Mail_AuthenticatorGenerator.getAuthenticator(userName, password));
		try {
			if (folder.exists())
				connect = true;
			else
				connect = false;
			newAllMessage = folder.getMessageCount();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		current_Count = newAllMessage;
	}

	public Folder fetchInbox(Properties props, Authenticator authenticator) {
		return fetchInbox(props, authenticator, null);
	}

	public Folder fetchInbox(Properties props, Authenticator authenticator, String protocol) {
		Session session = Session.getDefaultInstance(props, authenticator);
		Store store = null;
		Folder folder = null;
		try {
			store = protocol == null || protocol.trim().length() == 0 ? session.getStore() : session.getStore(protocol);
			store.connect();
			folder = store.getFolder("INBOX");// 获取收件箱
			folder.open(Folder.READ_ONLY); // 以只读方式打开
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return folder;
	}

	public boolean newMessage() {
		int newAllMessage = 0;
		Folder folder = fetchInbox(props, Mail_AuthenticatorGenerator.getAuthenticator(userName, password));
		try {
			newAllMessage = folder.getMessageCount();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		System.out.println("before: " + current_Count + ";current: " + newAllMessage);
		if (newAllMessage > current_Count){
			current_Count=newAllMessage;
			return true;
		}
		
		else{
			current_Count=newAllMessage;
			return false;
		}
	}

	public void sendMessage(String address, String body) {
		Session session = Session.getDefaultInstance(props,
				Mail_AuthenticatorGenerator.getAuthenticator(userName, password));
		MimeMessage message = new MimeMessage(session);
		try {
			InternetAddress from;
			from = new InternetAddress(userName);
			message.setFrom(from);
			InternetAddress to = new InternetAddress(address);
			message.setSubject("[IFTTT]Time_Message");
			message.setText(body);
			message.setRecipient(RecipientType.TO, to);
			Transport transport = session.getTransport("smtps");
			switch (mail_Host) {
			case NETEASE:
				transport.connect("smtp.163.com", 465, userName, password);
				break;
			case NETEASE2:
				transport.connect("smtp.yeah.net", 465, userName, password);
				break;
			case TENCENT:
				transport.connect("smtp.qq.com", 465, userName, password);
				break;
			default:
				break;
			}
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
}*/