package task.mail;

import java.util.Properties;

public enum Mail_Host {
	NETEASE2 {
		@Override
		public Properties getProperties() {
			Properties defaults = new Properties();
			defaults.put("mail.pop3.host", "pop.yeah.net");
			defaults.put("mail.imap.host", "imap.yeah.net");
			defaults.put("mail.store.protocol", "pop3");
			defaults.put("mail.smtp.host","smtp.yeah.net");
			return defaults;
		}

	},
	NETEASE {
		@Override
		public Properties getProperties() {
			Properties defaults = new Properties();
			defaults.put("mail.pop3.host", "pop.163.com");
			defaults.put("mail.imap.host", "imap.163.com");
			defaults.put("mail.store.protocol", "pop3");
			defaults.put("mail.stmp.host","smtp.163.com");
			return defaults;
		}

	},
	TENCENT {
		@Override
		public Properties getProperties() {
			Properties defaults = new Properties();
			defaults.put("mail.pop3.host", "pop.qq.com");
			defaults.put("mail.imap.host", "imap.qq.com");
			defaults.put("mail.store.protocol", "imap");
			defaults.put("mail.imap.port", "993");
			defaults.put("mail.imap.ssl.enable", "true");
			defaults.put("mail.imap.auth.plain.disable", "true");
			defaults.put("mail.imap.socketFactory", "javax.net.ssl.SSLSocketFactory");
			defaults.put("mail.imap.socketFactory.fallback", "false");
			defaults.put("mail.stmp.host","smtp.qq.com");
			return defaults;
		}
	};

	public abstract Properties getProperties();
}