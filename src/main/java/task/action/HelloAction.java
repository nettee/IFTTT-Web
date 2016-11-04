package task.action;

import java.util.HashMap;
import java.util.Map;

import model.task.Action;

public class HelloAction implements Action {

	private static final long serialVersionUID = 1L;

	@Override
	public int getType() {
		return HELLO;
	}

	@Override
	public void perform() {
		System.out.println("Hello world!");
	}

	@Override
	public Map<String, Object> getProperties() {
		// returns empty map
		return new HashMap<String, Object>();
	}

	@Override
	public String toString() {
		return "HelloAction{}";
	}

}
