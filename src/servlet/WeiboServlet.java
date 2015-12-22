package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import task.weibo.Auth;

public class WeiboServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2307835113833198475L;

	/**
	 * Constructor of the object.
	 */
	public WeiboServlet() {
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
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
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
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		if (request.getParameter("code") == null) {
			Auth.getCode();
			out.println("<body onload=\"javascript:window.close()\">");
			out.close();
		} else {
			String code = request.getParameter("code");
			log("get weibo code:"+code);
			response.setContentType("text/html");
			HttpSession session = request.getSession();
			session.setAttribute("code", code);
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><TITLE>WeiboServlet</TITLE></HEAD>");
			out.println("  <BODY>");
			out.print("    The Code is ");
			out.println(code);
			out.println("  </BODY>");
			out.println("</HTML>");
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
