<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<title>Sub page UserList of Admin Home</title>
<link href="css/materialize.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.11.3.min.js" > </script>
<script src="./js/materialize.js"></script>
</head>

<%@include file="Data_Sub.jsp" %>
<%@page import="model.data.User"%>
<%if(subId!=1) response.sendRedirect("dashboard.jsp"); %>
<body>
	<ul class="collapsible" data-collapsible="accordion" style="width: 80%">
	<li class="collection-item avatar">
		<div class="collapsible-header ">
		<i class="material-icons circle">account_circle</i>全站公告
		</div>
		<div class="collapsible-body center">
		<div class="mdl-textfield mdl-js-textfield">
            	<input class="mdl-textfield__input"type="text">
              	<label class="mdl-textfield__label">公告</label>
        </div>
        <a class="btn">Send</a>
        </div>
	</li>
		<%
			for (User u : subuser.getUserList()) {
		%>
		<li class="collection-item avatar">
			<div class="collapsible-header ">
				<i class="material-icons circle">account_circle</i>
				ID:<%=u.getId()%>&nbsp;&nbsp;用户名:<%=u.getName()%>
				<div class="btn" style="float: right;">Send</div>	
				<br>
				余额:<%=u.getBalance() %>
				 
			</div>
			<div class="collapsible-body"><p>任务列表</p></div>
		</li> 
		<%
			}
		%>
		
	</ul>
</body>