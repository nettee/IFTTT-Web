<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<title>Sub page Inbox of User Home</title>
</head>
<body>
	<jsp:useBean id="subuser" class="model.data.User"></jsp:useBean>
	<main class="mdl-layout__content">
	<%@page import="model.data.Message"%>
	<%
		Integer Id = null;
		if (session.getAttribute("userId") != null) {
			Id = (Integer) session.getAttribute("userId");
		}
		subuser.setThisById(Id);
		List<Message> message_list = subuser.getMessageList();
	%>
	<%
		for(Message m:message_list){
			%>
			<div class="demo-card--expand mdl-card mdl-shadow--2dp" style>
			<div class="mdl-card__title"><%=m.getPublishTime() %> </div>
			<div class="mdl-card__supporting-text"><%=m.getContent() %></div>
			</div>
			<% 
		}
	 %>

	 </main>
</body>
</html>
