package database;

import org.junit.Test;

public class DriverTest {
	
	@Test
	public void testDriver() throws ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
	}
}
