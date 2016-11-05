package database;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.Properties;

import org.junit.Test;

public class PropertiesTest {

	@Test
	public void testDatabaseProperties() throws IOException {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("database.properties");
		Properties properties = new Properties();
		properties.load(is);
		for (Entry<Object, Object> e : properties.entrySet()) {
			System.out.println(e.getKey() + "=" + e.getValue());
		}
	}

}
