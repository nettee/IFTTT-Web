package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import model.data.User;
import model.data.UserTask;

public class TaskOperateServlet extends HttpServlet {

	private static final Logger logger = Logger
			.getLogger(TaskOperateServlet.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 8784469792404425159L;

	/**
	 * Constructor of the object.
	 */
	public TaskOperateServlet() {
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
		UserTask task = null;
		String op = request.getParameter("op");
		User user = new User();
		user.setThisById((Integer) request.getSession().getAttribute("userId"));
		int id = 0;
		if (request.getParameter("id") != null) {
			id = Integer.valueOf(request.getParameter("id"));
			task = UserTask.getUserTask(id);
		}
		if (op != null) {
			if (op.equals("run"))// Run
			{
				String duration_string = request.getParameter("duration");
				int duration = Integer.valueOf(duration_string);
				if (task != null) {
					task.startRepeated(duration);
					logger.info(task.getTask().getName() + " Start for "
							+ duration);
				}
			} else if (op.equals("run_once"))// Run Once
			{
				if (task != null) {
					task.startOnce();
					logger.info(task.getTask().getName() + " Start for once");
				}
			} else if (op.equals("pause"))// Pause
			{
				if (task != null) {
					task.pause();
					logger.info(task.getTask().getName() + " Pause");
				}
			} else if (op.equals("stop"))// Stop
			{
				if (task != null) {
					task.stop();
					logger.info(task.getTask().getName() + " Stop");
				}
			} else if (op.equals("resume"))// Resume
			{
				if (task != null) {
					task.resume();
					logger.info(task.getTask().getName() + " Resume");
				}
			} else if (op.equals("edit"))// Edit
			{
				if(task!=null){
					request.getSession().setAttribute("Edit", id);
				}
			} else if (op.equals("delete"))// Delete
			{
				if(task!=null){
					task.delete();
					logger.info(task.getTask().getName() + " Deleted");
				}
			}
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
