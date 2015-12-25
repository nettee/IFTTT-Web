package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.data.User;

import org.apache.log4j.Logger;

import database.UserDao;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {

	private static final Logger logger = Logger.getLogger(LoginServlet.class);

	@Override
	public void init() throws ServletException {
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

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (UserDao.confirmPassword(username, password)) {
			logger.info(String.format("logged in, username=%s, password=%s",
					username, password));

			HttpSession session = request.getSession();
			User user = UserDao.getUserByName(username);
			session.setAttribute("userId", user.getId());

			String userHomePage = getServletContext().getInitParameter(
					"user home page");
			response.sendRedirect(userHomePage);
		} else {
			logger.info("cannot log in, invalid username or password");
			String loginPage = getServletContext().getInitParameter(
					"login page");
			String loginPage_Invalid = loginPage + "?valid=no";
			response.sendRedirect(loginPage_Invalid);
		}

	}

}
