<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<title>Sub page Inbox of User Home</title>
<link href="css/materialize.css" rel="stylesheet">
</head>
<body>
	<jsp:useBean id="subuser" class="model.data.User"></jsp:useBean>
	<ul class="collection" style="width: 80%">
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
			for (Message m : message_list) {
		%>
		<li class="collection-item avatar"><i
			class="material-icons circle">folder</i> <span class="title"><%=m.getDigest()%></span>
			<p><%=m.getPublishTime()%>
				<br>
				<%=m.getContent()%>
			</p> 
			<%if(m.isOpened()){%> <a href="#!" class="secondary-content" ><i class="material-icons">star</i></a><%}else{
			 %>  <a href="#!" class="secondary-content mdl-color-teal" style="color:gray;"><i class="material-icons">star</i></a><%} %>
		</li>
		<%
			}
		%>
	</ul>
	<script src="./js/materialize.js"></script>
</body>
</html>
