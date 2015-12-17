package model.task;

import java.io.Serializable;
import java.util.Map;

public interface Action extends Serializable {

	void perform();

	Map<String, Object> getProperties();
}
