package servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import database.UserDao;

@SuppressWarnings("serial")
public class RegisterServlet extends HttpServlet {

	private static final Logger logger = Logger
			.getLogger(RegisterServlet.class);

	private UserDao userDao;

	@Override
	public void init() throws ServletException {
		userDao = new UserDao();
	}

	@Override
	public void destroy() {
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		logger.info("doPost");
		
		ServletContext servletContext = getServletContext();
		logger.info("register page = " + servletContext.getAttribute("login page"));

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		logger.info(String.format("register: username=%s, password=%s",
				username, password));

		if (userDao.existsUser(username)) {
			logger.info("username already exists!");
			String registerPage = getServletContext().getInitParameter("register page");
			String registerPage_exist=registerPage+"?exist=yes";
			response.sendRedirect(registerPage_exist);
		} else {
			userDao.addUser(username, password);
			logger.info(String.format("register succeeded, username=%s",
					username));
			String loginPage = getServletContext().getInitParameter("login page");
			response.sendRedirect(loginPage);
		}
	}

}
