package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import task.action.HelloAction;
import task.trigger.MailReceivedTrigger;
import task.trigger.TimeTrigger;

import model.data.User;
import model.data.UserTask;
import model.task.Task;

public class TaskOperateServlet extends HttpServlet {

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
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserTask task=null;
		String op=request.getParameter("op");
		User user=new User();
		user.setThisById((Integer)request.getSession().getAttribute("userId"));
		int id=0;
		if(request.getParameter("id")!=null) 
		{
			id=Integer.valueOf(request.getParameter("id"));
			task=UserTask.getUserTask(id);
		}
		if(op!=null){
		if(op.equals("run"))//Run
		{
			String duration_string=request.getParameter("duration");
			int duration=Integer.valueOf(duration_string);
			if(task!=null) {task.startRepeated(duration); log(task.getTask().getName()+" Start for "+duration);}
		}else if(op.equals("run_once"))//Run Once
		{
			if(task!=null) {task.startOnce(); log(task.getTask().getName()+" Start for once");}
		}else if(op.equals("pause"))//Pause
		{
			
		}else if(op.equals("stop"))//Stop
		{
			
		}else if(op.equals("edit"))//Edit
		{
			
		}else if(op.equals("delete"))//Delete
		{
		
		}}
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
