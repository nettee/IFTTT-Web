<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<title>Sub page Inbox of User Home</title>
<link href="css/materialize.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.11.3.min.js" > </script>
<script src="./js/materialize.js"></script>
</head>
<%@page import="model.data.Message"%>
<%@include file="Data_Sub.jsp" %>
<%List<Message> message_list = subuser.getMessageList();%>
<body>
	<ul class="collection" style="width: 80%">
		<%
			for (Message m : message_list) {
		%>
		<li class="collection-item avatar"><i
			class="material-icons circle">message</i> <span class="title"><%=m.getSubject()%></span>
			<p><%=m.getPublishTime()%>
				<br>
				<%=m.getContent()%>
			</p> 
		<%
			}
			//全部设置为已读
		%>
		
	</ul>
</body>
</html>
