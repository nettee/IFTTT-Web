package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.data.User;

import org.apache.log4j.Logger;

public class ChargeServlet extends HttpServlet {
	private static final Logger logger = Logger.getLogger(ChargeServlet.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 6422990525683346021L;

	/**
	 * Constructor of the object.
	 */
	public ChargeServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			int id=(Integer)request.getSession().getAttribute("userId");
			String charge_str=request.getParameter("charge_num");
			int charge_num=Integer.valueOf(charge_str);
			User user=new User();
			user.setThisById(id);
			user.recharge(charge_num);
			logger.info("user"+id+" charge "+charge_num);
			response.sendRedirect("dashboard.jsp?page=Purchase");
		}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
