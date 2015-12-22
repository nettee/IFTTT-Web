<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!doctype html>
<%@page import="model.data.Message" %>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Dash board</title>
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
<%@include file="component/Data_Sub.jsp"%>
<%if(subId!=null) {Integer Unread=subuser.getUnopenedMessageNumber();%>
<body>
	<div
		class="demo-layout mdl-layout mdl-js-layout mdl-layout--fixed-drawer mdl-layout--fixed-header">
		<header
			class="demo-header mdl-layout__header mdl-color--grey-100 mdl-color-text--grey-600">
			<div class="mdl-layout__header-row">
				<span class="mdl-layout-title"><%String pageName=request.getParameter("page");if(pageName!=null){%><%=pageName %><%}else{%>Home<%} %></span>
				<div class="mdl-layout-spacer"></div>
				<button
					class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon"
					id="hdrbtn">
					<i class="material-icons">more_vert</i>
				</button>
				<ul
					class="mdl-menu mdl-js-menu mdl-js-ripple-effect mdl-menu--bottom-right" for="hdrbtn">

					<li class="mdl-menu__item"><a href="/about">About</a>
					</li>

					<li class="mdl-menu__item"><a href="/logout.do">Log Out</a>
					</li>

				</ul>
			</div>
		</header>
		<div
			class="demo-drawer mdl-layout__drawer mdl-color--blue-grey-900 mdl-color-text--blue-grey-50">
			<header class="demo-drawer-header">
				<img src="assets/user.jpg" class="demo-avatar">
				<div class="demo-avatar-dropdown">
					<span><jsp:getProperty name="subuser" property="name"/></span>
					<div class="mdl-layout-spacer"></div>
					<button id="accbtn"
						class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon">
						<i class="material-icons" >arrow_drop_down</i>
						<span class="visuallyhidden">Accounts</span>
					</button>
					<ul
						class="mdl-menu mdl-menu--bottom-right mdl-js-menu mdl-js-ripple-effect" for="accbtn">
						<li class="mdl-menu__item"><a href="login.jsp"><i
								class="material-icons">add</i>Add another account...</a>
						</li>
					</ul>
				</div>
			</header>
			<nav class="demo-navigation mdl-navigation mdl-color--blue-grey-800">
				<a class="mdl-navigation__link" href="?page=Home">
					<i class="mdl-color-text--blue-grey-400 material-icons">home</i>
					Home
				</a> 
				<a class="mdl-navigation__link" href="?page=Task">
					<i class="mdl-color-text--blue-grey-400 material-icons">toc</i>
					Tasks
				</a> 
				<a class="mdl-navigation__link" href="?page=Inbox">
					<% if(Unread>0) {%>
					<span class="mdl-badge" data-badge="<%=Unread%>">
					<i class="mdl-color-text--blue-grey-400 material-icons">inbox</i>Inbox</span><%}
					else{%>
					<i class="mdl-color-text--blue-grey-400 material-icons">inbox</i>Inbox<%} %>
				</a>
				<a class="mdl-navigation__link" href="?page=Setting">
					<i class="mdl-color-text--blue-grey-400 material-icons">settings</i>
					Setting
				</a>
				<a class="mdl-navigation__link" href="?page=Purchase">
					<i class="mdl-color-text--blue-grey-400 material-icons">shopping_cart</i>
					Purchases
				</a>
				<div class="mdl-layout-spacer"></div>
					<a class="mdl-navigation__link" href="?page=Help"><i class="mdl-color-text--blue-grey-400 material-icons">help_outline</i><span class="visuallyhidden">Help</span>
				</a>
			</nav>
		</div>
		<main class="mdl-layout__content">
		
		<%if(pageName!=null){
		 if(pageName.equals("Task")){ %><jsp:include page="component/Task_Sub.jsp"></jsp:include><% }
		 else if(pageName.equals("Inbox")){%><jsp:include page="component/Inbox_Sub.jsp"></jsp:include><% } 
		 else if(pageName.equals("Setting")){%><jsp:include page="component/Setting_Sub.jsp"></jsp:include><% }
		 else if(pageName.equals("Purchase")){%><jsp:include page="component/Purch_Sub.jsp"></jsp:include><% }
		 else if(pageName.equals("Help")){%><jsp:include page="component/Help_Sub.jsp"></jsp:include><% }
		 else {%><jsp:include page="component/Home_Sub.jsp"></jsp:include><% }
		}else{ %><jsp:include page="component/Home_Sub.jsp"></jsp:include><%}%>
		</main>
	</div>
	<script src="./js/material.js"></script>
</body>
<%} %>
</html>
