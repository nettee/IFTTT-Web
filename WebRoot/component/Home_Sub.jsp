<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<title>Sub page Home of User Home</title>
<link href="css/materialize.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.11.3.min.js" > </script>
<script src="./js/materialize.js"></script>
</head>
<%@include file="Data_Sub.jsp" %>
  <body>
    <div class="mdl-grid demo-content">
			<div class="demo-graphs mdl-shadow--2dp mdl-color--white mdl-cell mdl-cell--8-col">
			<div class="col s12 m8 offset-m2 l6 offset-l3">
        <div class="card-panel grey lighten-5 z-depth-1">
          <div class="row valign-wrapper">
            <div class="col s2">
              <img src="assets/user.jpg" alt="" class="circle responsive-img"> <!-- notice the "circle" class -->
            </div>
            <div class="col s10">
              <span class="black-text">
              	<h4>Welcome user <a href="?page=Setting"><jsp:getProperty name="subuser" property="name"/></a>!</h4>
              	<span>积分:<%=subuser.getScore() %> </span>
              	<span>余额:<a href="?page=Purchase" class="tooltipped mdl-color-text--pink" data-tooltip="充值"><jsp:getProperty name="subuser" property="balance"/></a></span>
              </span>
            </div>
          </div>
        </div>
      </div>
      <div><h6>Descriptions about IFTTT</h6></div>
              	</div>
			<div
				class="demo-cards mdl-cell mdl-cell--4-col mdl-cell--8-col-tablet mdl-grid mdl-grid--no-spacing">
				<div
					class="demo-updates mdl-card mdl-shadow--2dp mdl-cell mdl-cell--4-col mdl-cell--4-col-tablet mdl-cell--12-col-desktop">
					<div class="mdl-card__title mdl-card--expand mdl-color--teal-300">
						<h2 class="mdl-card__title-text">Announcement</h2>
					</div>
					<div class="mdl-card__supporting-text mdl-color-text--grey-600">
						<%=subuser.getMessageList().get(subuser.getMessageList().size()-1).getContent() %></div>
					<div class="mdl-card__actions mdl-card--border">
						<a href="?page=Inbox" class="mdl-button mdl-js-button mdl-js-ripple-effect">Read
							More</a>
					</div>
				</div>
				<div class="demo-separator mdl-cell--1-col"></div>
				<div
					class="demo-options mdl-card mdl-color--deep-purple-500 mdl-shadow--2dp mdl-cell mdl-cell--4-col mdl-cell--3-col-tablet mdl-cell--12-col-desktop">
					<div class="mdl-card__supporting-text mdl-color-text--blue-grey-50">
						<h3>Your Tasks</h3>
						<ul>
							<li>最近任务:
							<div class="center">
								<h6><%=subuser.getTaskList().get(subuser.getTaskList().size()-1).getTask().getName() %></h6></div>		</li>
								
							<li>任务状态:
							<br>
								<%int status=subuser.getTaskList().get(subuser.getTaskList().size()-1).getStatus();
								  String Dis=null;
								  switch(status){
								  case 0:Dis="已停止";break;
								  case 1:Dis="正在运行";break;
								  case 2:Dis="已暂停";break;
								  default:Dis="null";break;
								  }									
								%>
								<div class="center">
								<h6>
								<%=Dis %>
								</h6>
								</div>
							</li>
						</ul>
					</div>
					<div class="mdl-card__actions mdl-card--border">
						<a href="?page=Task"
							class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-color-text--blue-grey-50">
							Task Manager</a>
						<div class="mdl-layout-spacer"></div>
					</div>
				</div>
			</div>
		</div>
