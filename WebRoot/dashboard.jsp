<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description"
	content="A front-end template that helps you build fast, modern mobile web apps.">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Dashboard</title>
<link href="css/font.css" rel="stylesheet">
<link href="css/icon.css" rel="stylesheet">
<link rel="stylesheet" href="css/material.css">
<link rel="stylesheet" href="css/dashboard.css">
<style>
#view-source {
	position: fixed;
	display: block;
	right: 0;
	bottom: 0;
	margin-right: 40px;
	margin-bottom: 40px;
	z-index: 900;
}
.parent {
    overflow: hidden;
    position: relative;
    width: 100%;
}
a {
	text-decoration: none;
}
</style>
</head>
<body>
<%
	Integer userId=null;
	if(session.getAttribute("userId")!=null){
		userId=(Integer)session.getAttribute("userId");
	}
	else response.sendRedirect("login.jsp");
%>
<jsp:useBean id="user" class="model.data.User"></jsp:useBean>
	<%if(userId!=null)user.setThisById(userId);%>
<jsp:useBean id="message" class="model.data.Message"></jsp:useBean>
	<%if(userId!=null)message.setId(userId); %>

	<div
		class="demo-layout mdl-layout mdl-js-layout mdl-layout--fixed-drawer mdl-layout--fixed-header">
		<header
			class="demo-header mdl-layout__header mdl-color--grey-100 mdl-color-text--grey-600">
			<div class="mdl-layout__header-row">
				<span class="mdl-layout-title"><%String pageName=request.getParameter("page");if(pageName!=null){%><%=pageName %><%}else{%>Home<%} %></span>
				<div class="mdl-layout-spacer"></div>
				<div
					class="mdl-textfield mdl-js-textfield mdl-textfield--expandable">
					<label class="mdl-button mdl-js-button mdl-button--icon"
						for="search"> <i class="material-icons">search</i> </label>
					<div class="mdl-textfield__expandable-holder">
						<input class="mdl-textfield__input" type="text" id="search">
						<label class="mdl-textfield__label" for="search">Enter
							your query...</label>
					</div>
				</div>
				<button
					class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon"
					id="hdrbtn">
					<i class="material-icons">more_vert</i>
				</button>
				<ul
					class="mdl-menu mdl-js-menu mdl-js-ripple-effect mdl-menu--bottom-right"
					for="hdrbtn">

					<li class="mdl-menu__item"><a href="/about">About</a>
					</li>

					<li class="mdl-menu__item"><a href="/login.jsp">Log Out</a>
					</li>

				</ul>
			</div>
		</header>
		<div
			class="demo-drawer mdl-layout__drawer mdl-color--blue-grey-900 mdl-color-text--blue-grey-50">
			<header class="demo-drawer-header">
				<img src="assets/user.jpg" class="demo-avatar">
				<div class="demo-avatar-dropdown">
					<span><jsp:getProperty name="user" property="name"/></span>
					<div class="mdl-layout-spacer"></div>
					<button id="accbtn"
						class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon">
						<i class="material-icons" role="presentation">arrow_drop_down</i>
						<span class="visuallyhidden">Accounts</span>
					</button>
					<ul
						class="mdl-menu mdl-menu--bottom-right mdl-js-menu mdl-js-ripple-effect"
						for="accbtn">
						<li class="mdl-menu__item"><a href="login.jsp"><i
								class="material-icons">add</i>Add another account...</a>
						</li>
					</ul>
				</div>
			</header>
			<nav class="demo-navigation mdl-navigation mdl-color--blue-grey-800">
				<a class="mdl-navigation__link" href="?page=Home"><i
					class="mdl-color-text--blue-grey-400 material-icons"
					role="presentation">home</i>Home</a> <a class="mdl-navigation__link"
					href="?page=Task"><i class="mdl-color-text--blue-grey-400 material-icons"
					role="presentation">toc</i>Tasks</a> <a class="mdl-navigation__link"
					href="?page=Inbox">
					<% if(user.getName().equals("rg4")) {%><span class="mdl-badge" data-badge="n"><i class="mdl-color-text--blue-grey-400 material-icons"
					role="presentation">inbox</i>Inbox</span><%}else{%><i class="mdl-color-text--blue-grey-400 material-icons"
					role="presentation">inbox</i>Inbox<%} %>
					</a> <a class="mdl-navigation__link"
					href="?page=Setting"><i class="mdl-color-text--blue-grey-400 material-icons"
					role="presentation">settings</i>Setting</a> <a
					class="mdl-navigation__link" href="?page=Report"><i
					class="mdl-color-text--blue-grey-400 material-icons"
					role="presentation">report</i>Report</a> <a
					class="mdl-navigation__link" href="?page=Purchase"><i
					class="mdl-color-text--blue-grey-400 material-icons"
					role="presentation">shopping_cart</i>Purchases</a>
				<div class="mdl-layout-spacer"></div>
				<a class="mdl-navigation__link" href="?page=Help"><i
					class="mdl-color-text--blue-grey-400 material-icons"
					role="presentation">help_outline</i><span class="visuallyhidden">Help</span>
				</a>
			</nav>
		</div>
		<main class="mdl-layout__content mdl-color--grey-100 parent">
		
		<%if(pageName!=null){
		 if(pageName.equals("Task")){ %><%@include file="component/Task_Sub.jsp" %><% }
		 else if(pageName.equals("Inbox")){%><%@include file="component/Inbox_Sub.jsp" %><% } 
		 else if(pageName.equals("Setting")){%><%@include file="component/Setting_Sub.jsp" %><% }
		 else if(pageName.equals("Report")){%><%@include file="component/Report_Sub.jsp" %><% }
		 else if(pageName.equals("Purchase")){%><%@include file="component/Purch_Sub.jsp" %><% }
		 else if(pageName.equals("Help")){%><%@include file="component/Help_Sub.jsp" %><% }
		 else {%><%@include file="component/Home_Sub.jsp" %><% }
		}else{ %><%@include file="component/Home_Sub.jsp" %><%}%>
		</main>
	</div>
	<script src="./js/material.js"></script>
</body>
</html>
