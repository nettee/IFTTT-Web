package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import database.UserDao;
import model.data.User;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(LoginServlet.class);

	@Override
	public void init() throws ServletException {
	}

	@Override
	public void destroy() {
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (UserDao.confirmPassword(username, password)) {
			logger.info("logged in, username={}, password={}", username, password);

			HttpSession session = request.getSession();
			User user = UserDao.getUserByName(username);
			session.setAttribute("userId", user.getId());

			String userHomePage = getServletContext().getInitParameter("user home page");
			response.sendRedirect(userHomePage);
		} else {
			logger.info("cannot log in, invalid username or password");
			String loginPage = getServletContext().getInitParameter("login page");
			String loginPage_Invalid = loginPage + "?valid=no";
			response.sendRedirect(loginPage_Invalid);
		}

	}

}
