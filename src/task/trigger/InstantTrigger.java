package task.trigger;

import java.util.HashMap;
import java.util.Map;

import model.task.Trigger;

public final class InstantTrigger implements Trigger {

	private static final long serialVersionUID = 1L;
	
	@Override
	public int getType() {
		return INSTANT;
	}

	@Override
	public boolean test() {
		// simply return true
		return true;
	}

	@Override
	public Map<String, Object> getProperties() {
		// returns empty map
		return new HashMap<String, Object>();
	}

}
