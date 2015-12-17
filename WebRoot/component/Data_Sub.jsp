<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<jsp:useBean id="subuser" class="model.data.User"></jsp:useBean>
<%
	Integer subId=null;
	if(session.getAttribute("userId")!=null){
		subId=(Integer)session.getAttribute("userId");
	}
	else response.sendRedirect("login.jsp");
%>
<% if(subId!=null) subuser.setThisById(subId); %>