<%@page import="model.data.UserTask"%>
<%@page import="model.task.Task"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<title>Sub page Task of User Home</title> 
<link href="css/materialize.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.11.3.min.js" > </script>
<script src="./js/materialize.js"></script>
<%@include file="Data_Sub.jsp" %>
</head>
<style>
.task_background {
	background: url('') center/cover;
}
</style>
<body>
	<div class="demo-card-wide">
		<ul class="collection" style="width: 80%">
		<%@page import="model.*" %>
		<%for(UserTask t:subuser.getTaskList()) { Task tb=t.getTask();%>
		<li class="collection-item avatar">
			<i class="material-icons circle">view_headline</i>
			<span class="title"><%=tb.getName()%></span>
			<p>
			<%=tb.toString() %>
				<br>
			</p>
		</li>
		<%} %>
		</ul>
	</div>
<ul class="collapsible" data-collapsible="accordion" style="width: 80%">
<li class="collection-item" style="width: 80%;">
<div class="collapsible-header">Create Task</div>
<div class="collapsible-body">
      <form action="/Create" method="post" >
      <div class="row">
      	<div class="col s12">
      		<ul class="tabs">
        		<li class="tab col s3 disabled"><a href="#test1" style="color:black !important;">If</a></li>
        		<li class="tab col s3"><a  href="#this" class="active">This</a></li>
        		<li class="tab col s3 disabled"><a href="#test1" style="color:black !important;">Then</a></li>
        		<li class="tab col s3" ><a href="#that">That</a></li>
      		</ul>
    	</div>
    	<div id="this" class="col s12">
					<div class="col s3" style="padding-top: 20px">
						<div class="center">
							<img alt="if1" src="assets/weibo.png" style="width:50px;height: 40px;">
							<p class="center " style="font-weight: bold;font-size:large;">新浪微博</p>
							<p class="center"><input name="group1" type="radio" id="o1" value="o1"/><label for="o1"></label></p>
							<p class="light center">当指定用户发布包含指定内容的微博时</p>
							<a class="btn" target="_parent" href="/weibo">授权</a>
							<%if(session.getAttribute("accessToken")!=null) {%><p class="center">已授权</p><%} %>
							<div class="center">
							<div class="mdl-textfield mdl-js-textfield">
								<input class="mdl-textfield__input" id="wc"	name="weibo_content"> <label class="mdl-textfield__label">指定内容</label>
							</div>
							</div>
						</div>
     				</div>
     				<div class="col s3" style="padding-top: 20px">
						<div class="center">
							<img alt="if1" src="assets/weibo.png" style="width:50px;height: 40px;">
							<p class="center " style="font-weight: bold;font-size:large;">新浪微博</p>
							<p class="center"><input name="group1" type="radio" id="o2" value="o2"/><label for="o2"></label></p>
							<p class="light center">当用户在指定时间段内未发布微博时</p>
							<a class="btn" target="_parent" href="/weibo">授权</a>
							<%if(session.getAttribute("accessToken")!=null) {%><p class="center">已授权</p><%} %>
							<div class="center">
							<div class="mdl-textfield mdl-js-textfield">
								<input class="mdl-textfield__input center" id="wt"	type="time" name="weibo_time"> 
							</div>
							</div>
						</div>
     				</div>
      				<div class="col s3" style="padding-top: 20px">
						<div class="center">
							<img alt="if2" src="assets/mail.png" style="width:50px;height: 40px;">
							<p class="center " style="font-weight: bold;font-size:large;">邮件</p>
							<p class="center"><input name="group1" type="radio" id="o3" value="o3"/><label for="o3"></label></p>
							<p class="light center">当指定邮箱收到邮件时</p></div>
							<div class="mdl-textfield mdl-js-textfield">
								<input class="mdl-textfield__input" type="text" id="mn"	 name="mail1_name"> <label class="mdl-textfield__label">邮箱</label>
							</div>
							<div class="center">
							<div class="mdl-textfield mdl-js-textfield">
								<input class="mdl-textfield__input" id="mp"	type="password" name="mail1_password"> <label class="mdl-textfield__label">密码</label>
							</div>
							</div>
     				</div>
     				<div class="col s3" style="padding-top: 20px">
						<div class="center">
							<img alt="if3" src="assets/clock.png" style="width:50px;height: 40px;">
							<p class="center " style="font-weight: bold;font-size:large;">时钟</p>
							<p class="center"><input name="group1" type="radio" id="o4" value="o4"/><label for="o4"></label></p>
							<p class="light center">当前时间为指定时间时</p></div>
							<div class="center">
							<div class="mdl-textfield mdl-js-textfield">
								<input class="mdl-textfield__input center" id="t"	type="time" name="time"> <label class="mdl-textfield__label"></label>
							</div>
							</div>
     				</div>				
			</div>
    		<div id="that" class="col s12">
    			<div class="col s6" style="padding-top: 20px">
						<div class="center">
							<img alt="if1" src="assets/weibo.png" style="width:50px;height: 40px;">
							<p class="center " style="font-weight: bold;font-size:large;">新浪微博</p>
							<p class="center"><input name="group2" type="radio" value="o5" id="o5" /><label for="o5"></label></p>
							<p class="light center">发送微博</p>
							<a class="btn" href="/weibo">授权</a>
							<%if(session.getAttribute("accessToken")!=null) {%><p class="center">已授权</p><%} %>
							<div class="center">
							<div class="mdl-textfield mdl-js-textfield">
								<input class="mdl-textfield__input" id="pwc" 	name="post_sweibo_content"> <label class="mdl-textfield__label">指定内容</label>
							</div>
							</div>
						</div>
     				</div>
      				<div class="col s6" style="padding-top: 20px">
						<div class="center">
							<img alt="if2" src="assets/mail.png" style="width:50px;height: 40px;">
							<p class="center " style="font-weight: bold;font-size:large;">邮件</p>
							<p class="center"><input name="group2" type="radio" value="o6" id="o6" /><label for="o6"></label></p>
							<p class="light center">用指定邮箱向另一个邮箱发送邮件</p></div>
							<div class="row center">
							<!-- 
							<div class="mdl-textfield mdl-js-textfield">
								<input class="mdl-textfield__input" id="mn2"	name="mail2_name"> <label class="mdl-textfield__label">发送邮箱</label>
							</div>
							<div class="mdl-textfield mdl-js-textfield">
								<input class="mdl-textfield__input" id="mp2" type="password" name="mail2_password"> <label class="mdl-textfield__label">密码</label>
							</div>
							</div>
							 -->
							<div class="row center">
							<div class="mdl-textfield mdl-js-textfield">
								<input class="mdl-textfield__input" id="mn3"	name="post_to_address"> <label class="mdl-textfield__label">发送至邮箱</label>
							</div>
							<div class="mdl-textfield mdl-js-textfield">
								<input class=" mdl-textfield__input" id="ms"	name="mail_subject"> <label class="mdl-textfield__label">主题</label>
							</div>
							<div class="mdl-textfield mdl-js-textfield">
								<input class=" mdl-textfield__input" id="mc"	name="mail_content"> <label class="mdl-textfield__label">指定内容</label>
							</div>
							</div>
     				</div>
     				
		</div>
		</div>
		<div class="center">
			<input	class="center btn" type="submit" value="Create">
		</div>
		</form>
		</div>
		</li>
		<li class="collection-item" style="width: 80%;"><div class="collapsible-header">close</div><div class="collapsible-body"><p></p></div></li>
		</ul>
  </body>
</html>
