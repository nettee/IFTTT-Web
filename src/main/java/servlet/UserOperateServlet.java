package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.data.Admin;
import model.data.User;

import org.apache.log4j.Logger;

public class UserOperateServlet extends HttpServlet {
	private static final Logger logger = Logger
			.getLogger(UserOperateServlet.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 5629804702661383414L;

	/**
	 * Constructor of the object.
	 */
	public UserOperateServlet() {
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
		String operate_id_str=request.getParameter("id");
		String level_str=request.getParameter("level");
		String password=request.getParameter("password");
		if(content!=null&&operate_id_str==null){
			User user=new User();
			user.setThisById(id);
			logger.info("send message:"+content);
			Admin admin=new Admin(user);
			admin.sendMessageToAll("全站通知", content);
		}
		else if(content!=null&&operate_id_str!=null){
			logger.info("send message:"+content+"to user"+operate_id_str);
			int op_id=Integer.valueOf(operate_id_str);
			User user=new User();
			user.setThisById(id);
			Admin admin=new Admin(user);
			admin.sendMessageTo(op_id,"私信", content);
		}else if(content==null&&operate_id_str!=null){
			logger.info("change user "+operate_id_str+"to level"+level_str);
			int op_id=Integer.valueOf(operate_id_str);
			int level=Integer.valueOf(level_str);
			User user=new User();
			user.setThisById(id);
			Admin admin=new Admin(user);
			admin.setUserLevel(op_id, level);
		}
		else if(password!=null){
			logger.info("user "+id+"change password to"+password);
			User user=new User();
			user.setThisById(id);
			user.changePassword(password);
		}else{
			logger.info("error oprate in Userlist page");
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
