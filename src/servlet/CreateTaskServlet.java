package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.data.User;

public class CreateTaskServlet extends HttpServlet {

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
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StringBuffer tempInfo = new StringBuffer("");
		tempInfo.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"><HTML><HEAD><TITLE>Task Create Info</TITLE></HEAD>");
		Boolean Judge=true;User user=new User();user.setThisById((Integer)request.getSession().getAttribute("userId"));
		String option_this=request.getParameter("group1");
		String option_that=request.getParameter("group2");
		tempInfo.append("<BODY>User  "+user.getName()+ " Create Task Failed<div>Cause:</div>");
		if(option_this!=null){
		if(option_this.equals("o1")){
			
		}else if(option_this.equals("o2")){
			
		}else if(option_this.equals("o3")){
			
		}else if(option_this.equals("o4")){
			
		}else{
			Judge=false;
			tempInfo.append("<div>Choose no Option for This</div>");
		}
		}else {
			Judge=false;
			tempInfo.append("<div>Choose no Option for This</div>");
		}
		if(option_that!=null){
		if(option_that.equals("o5")){
			
		}else if(option_that.equals("o6")){
			
		}
		}else{
			Judge=false;
			tempInfo.append("<div>Choose no Option for That</div>");
		}
		tempInfo.append("<a href=\"dashboard.jsp?page=Task\" >Back</a>");
		tempInfo.append("</BODY></HTML>");
		if(Judge){
			log("Create:Format True");
			response.sendRedirect("dashboard.jsp?page=Task");
		}else{
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
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
