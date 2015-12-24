package model.task;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public interface Trigger extends Serializable {
	
	int INSTANT = 0;
	int TIME = 1;
	int MAIL_RECEIVED = 2;
	int WEIBO_PUSHED = 3;
	int WEIBO_SILENT = 4;
	
	Map<Integer, String> names = new HashMap<Integer, String>() {
		private static final long serialVersionUID = 1L;
		{
			put(INSTANT, "Instant Trigger");
			put(TIME, "Time Trigger");
			put(MAIL_RECEIVED, "Mail Received Trigger");
			put(WEIBO_PUSHED, "Weibo Pushed Trigger");
			put(WEIBO_SILENT, "Weibo Silent Trigger");
		}
	};
	
	int getType();
	
	boolean test();

	Map<String, Object> getProperties();

}
