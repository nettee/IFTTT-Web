package model.task;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public interface Action extends Serializable {

	int HELLO = 0;
	int MAIL_SENDING = 1;
	int WEIBO_POSTING = 2;

	Map<Integer, String> names = new HashMap<Integer, String>() {
		private static final long serialVersionUID = 1L;
		{
			put(HELLO, "Hello Action");
			put(MAIL_SENDING, "Mail Sending Action");
			put(WEIBO_POSTING, "Weibo Posting Action");
		}
	};
	
	int getType();

	void perform();

	Map<String, Object> getProperties();
}
