package model.task;

import java.io.Serializable;

public interface Trigger extends Serializable {
	
	boolean test();

}
