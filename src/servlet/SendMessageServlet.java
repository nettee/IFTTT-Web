package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.data.Admin;
import model.data.User;

import org.apache.log4j.Logger;

public class SendMessageServlet extends HttpServlet {
	private static final Logger logger = Logger
			.getLogger(SendMessageServlet.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 5629804702661383414L;

	/**
	 * Constructor of the object.
	 */
	public SendMessageServlet() {
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
		String content=request.getParameter("content");
		int id=(Integer)request.getSession().getAttribute("userId");
		User user=new User();
		user.setThisById(id);
		
		if(content!=null){
			logger.info("send message:"+content);
			Admin admin=new Admin(user);
			admin.sendMessageToAll("ȫվ֪ͨ", content);
		}
		
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
