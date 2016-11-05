package servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.PropertyConfigurator;

@SuppressWarnings("serial")
public class Log4jInit extends HttpServlet {

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
//		String prefix = getServletContext().getRealPath("/");
//		String file = getInitParameter("log4j");

		String path = Thread.currentThread().getContextClassLoader().getResource("log4j.properties").getPath();
		if (path != null) {
			PropertyConfigurator.configure(path);
			System.out.println("log4j successfully initialized");
		} else {
			System.out.println("log4j initialization failed");
		}
	}

}
