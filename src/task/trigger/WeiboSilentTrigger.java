package task.trigger;

import java.util.HashMap;
import java.util.Map;

import model.task.Trigger;

public class WeiboSilentTrigger implements Trigger {

	private static final long serialVersionUID = 1L;
	
	public WeiboSilentTrigger() {
		// TODO
	}

	@Override
	public int getType() {
		return WEIBO_SILENT; 
	}

	@Override
	public boolean test() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Map<String, Object> getProperties() {
		return new HashMap<String, Object>() {
			private static final long serialVersionUID = 1L;
			{
				// TODO
			}
		};
	}

}
