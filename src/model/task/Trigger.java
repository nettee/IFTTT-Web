package model.task;

import java.io.Serializable;
import java.util.Map;

public interface Trigger extends Serializable {
	
	boolean test();
	
	Map<String, Object> getProperties();

}
