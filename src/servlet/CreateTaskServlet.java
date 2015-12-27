package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import task.trigger.*;
import task.action.*;

import model.data.User;
import model.data.UserTask;
import model.task.Action;
import model.task.Task;
import model.task.Trigger;

public class CreateTaskServlet extends HttpServlet {

	private static final Logger logger = Logger
			.getLogger(CreateTaskServlet.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = -4869594442922570387L;

	/**
	 * Constructor of the object.
	 */
	public CreateTaskServlet() {
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
		StringBuffer tempInfo = new StringBuffer("");
		tempInfo.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"><HTML><HEAD><TITLE>Task Create Info</TITLE></HEAD>");

		User user = new User();
		user.setThisById((Integer) request.getSession().getAttribute("userId"));
		String accessToken = (String) request.getSession().getAttribute(
				"accessToken");
		Action action = null;
		Trigger trigger = null;
		String option_this = request.getParameter("group1");
		String option_that = request.getParameter("group2");
		String submit_type = request.getParameter("submit_type");
		// Set Task Name as "User TaskNo"
		Task task = new Task(user.getName() + " Task"
				+ user.getTaskList().size());

		Boolean Judge = true;
		tempInfo.append("<BODY>User  " + user.getName()
				+ " Create Task Failed<div>Cause:</div>");
		if (option_this != null) {
			if (option_this.equals("o1")) {
				String content = request.getParameter("weibo_content");
				// pattern可以为空
				if (accessToken != null)
					trigger = new WeiboPostedTrigger(user.getName(),
							accessToken, content);
				else {
					tempInfo.append("<div>No Authority for weibo</div>");
					logger.info("No Authority For Weibo");
					Judge = false;
				}
			} else if (option_this.equals("o2")) {
				String weibo_time = request.getParameter("weibo_time");
				if (accessToken != null && weibo_time != null
						&& !weibo_time.equals("")) {
					int hour = Integer.valueOf(weibo_time.split(":")[0]);
					int minute = Integer.valueOf(weibo_time.split(":")[1]);
					trigger = new WeiboSilentTrigger(accessToken, hour, minute);
				} else {
					Judge = false;
					if (accessToken != null) {
						tempInfo.append("<div>No Time set for weibo Trigger</div>");
						logger.info("No Time Set For Weibo Trigger");
					} else {
						tempInfo.append("<div>No Authority for weibo</div>");
						logger.info("No Authority For Weibo");
					}
				}
			} else if (option_this.equals("o3")) {
				String address = request.getParameter("mail1_name");
				String password = request.getParameter("mail1_password");
				if (address != null && password != null && !address.equals("")
						&& !password.equals(""))
					trigger = new MailReceivedTrigger(address, password);
				else {
					Judge = false;
					tempInfo.append("<div>Wrong Mail Info For Trigger</div>");
					logger.info("Wrong Mail Info For Trigger");
				}
			} else if (option_this.equals("o4")) {
				String time = request.getParameter("time");
				if (time != null && !time.equals("")) {
					int hour = Integer.valueOf(time.split(":")[0]);
					int minute = Integer.valueOf(time.split(":")[1]);
					trigger = new TimeTrigger(hour, minute);
				} else {
					Judge = false;
					tempInfo.append("<div>No Time Set For Trigger</div>");
					logger.info("No Time Set For Trigger");
				}
			} else {
				Judge = false;
				tempInfo.append("<div>No Option For Trigger</div>");
				logger.info("No Option For Trigger");
			}
		} else {
			Judge = false;
			tempInfo.append("<div>No Option For Trigger</div>");
			logger.info("No Option For Trigger");
		}
		if (option_that != null) {
			if (option_that.equals("o5")) {
				String post_weibo_content = request
						.getParameter("post_sweibo_content");
				if (accessToken != null && post_weibo_content != null
						&& !post_weibo_content.equals(""))
					action = new WeiboPostingAction(user.getName(),
							accessToken, post_weibo_content);
				else {
					Judge = false;
					if (accessToken == null) {
						tempInfo.append("<div>No Authority for weibo</div>");
						logger.info("No Authority for Weibo");
					} else {
						tempInfo.append("<div>No Content for Weibo sending</div>");
						logger.info("No Content for Weibo sending");
					}
				}
			} else if (option_that.equals("o6")) {
				String address = request.getParameter("post_to_address");
				String subject = request.getParameter("mail_subject");
				String content = request.getParameter("mail_content");
				if (address != null && !address.equals(""))
					action = new MailSendingAction(address, subject, content);
				else {
					Judge = false;
					tempInfo.append("<div>No Addresss For Post</div>");
					logger.info("No Option for That");
				}
			} else if (option_that.equals("o7")) {
				action = new HelloAction();
			}
		} else {
			Judge = false;
			tempInfo.append("<div>Choose no Option for Action</div>");
			logger.info("No Option for Action");
		}
		tempInfo.append("<a href=\"dashboard.jsp?page=Task\" >Back</a>");
		tempInfo.append("</BODY></HTML>");
		if (Judge) {
			logger.info("Format True");
			task.setAction(action);
			task.setTrigger(trigger);
			if (submit_type.equals("create")
					|| request.getSession().getAttribute("Edit") == null) {
				user.addTask(task);
				logger.info("Add Task:" + task.toString());
				response.sendRedirect("dashboard.jsp?page=Task");
			} else if (submit_type.equals("edit")) {
				Integer edit_id = (Integer) request.getSession().getAttribute(
						"Edit");
				UserTask edit_task = UserTask.getUserTask(edit_id);
				edit_task.editTask(task);
				request.getSession().removeAttribute("Edit");
				logger.info("Edit Task to:" + task.toString());
				response.sendRedirect("dashboard.jsp?page=Task");
			}
		} else {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print(tempInfo);
			out.flush();
			out.close();
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
