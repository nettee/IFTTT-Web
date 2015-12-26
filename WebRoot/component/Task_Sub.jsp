<%@page import="task.run.UserTaskStatus"%>
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
	<div class="demo-card-wide" id="tasklist">
		<ul class="collection" style="width: 80%">
		<%@page import="model.*" %>
		<% List<UserTask> tasklist=subuser.getTaskList();
		int Status=-1;
		for(UserTask t:tasklist) { Task tb=t.getTask();
		Status=t.getStatus();
		%>
		<li class="collection-item avatar">
		<%if(Status==0) {%>
			<i class="material-icons circle red tooltipped" data-tooltip="End">lens</i>
		<%} %>
		<%if(Status==1) {%>
			<i class="material-icons circle green tooltipped" data-tooltip="Running">lens</i>
		<%} %>
		<%if(Status==2) {%>
			<i class="material-icons circle yellow tooltipped" data-tooltip="Suspend">lens</i>
		<%} %>
			<span class="title"><%=tb.getName()%></span>
			<p>
			 <div style="position: relative;float: right" class="fixed-action-btn horizontal">
    <a class="btn-floating btn-large grey">
      <i class="material-icons">mode_edit</i>
    </a>
    <ul>
	  <%if(Status==2) {%>
	  <li><a class="op btn-floating green tooltipped" data-tooltip="Resume" data-op="resume" data-id=<%=t.getId()%> ><i class="material-icons">trending_flat</i></a></li>
	   <%} %>   
	   <%if(Status==0) {%>
      <li><a class="op btn-floating green lighten-2 tooltipped" data-tooltip="Run task repeatly" data-op="run" data-id=<%=t.getId()%> ><i class="material-icons">repeat</i></a></li>
      <li><a class="op btn-floating green darken-2 tooltipped" data-tooltip="Run task once" data-op="run_once" data-id=<%=t.getId()%> ><i class="material-icons">play_arrow</i></a></li>
      <%} %>
      <%if(Status==1) {%>
      <li><a class="op btn-floating yellow darken-1 tooltipped" data-tooltip="Pause task" data-op="pause" data-id=<%=t.getId()%> ><i class="material-icons">pause</i></a></li>
      <%} %>
       <%if(Status==1||Status==2) {%>
      <li><a class="op btn-floating red tooltipped" data-tooltip="Stop task" data-op="stop" data-id=<%=t.getId()%> ><i class="material-icons">stop</i></a></li>
      <%} %>
      <%if(Status==0){ %>
      <li><a class="op btn-floating purple tooltipped" data-tooltip="Edit task" data-op="edit" data-id=<%=t.getId()%> ><i class="material-icons">mode_edit</i></a></li>
      <li><a class="op btn-floating blue tooltipped" data-tooltip="Delete task" data-op="delete" data-id=<%=t.getId()%> ><i class="material-icons">delete</i></a></li>
      <%} %>
      
   			 </ul>
 			 </div>
			<%=tb.toString() %>
				<br>
		</li>
		<%} %>
		</ul>
	</div>
<ul class="collapsible" data-collapsible="expandable" style="width: 80%">
<li class="collection-item createform" style="width: 80%;">
<div class="collapsible-header">Create Task</div>
<div class="collapsible-body">
      <form action="/Create" method="post" name="">
      <input type="hidden" value="create" name="submit_type">
      <div class="row">
      	<div class="col s12">
      		<ul class="tabs">
        		<li class="tab col s3 disabled"><a href="#test1" style="color:black !important;">If</a></li>
        		<li class="tab col s3"><a  href="#this" class="active">This</a></li>
        		<li class="tab col s3 disabled"><a href="#test1" style="color:black !important;">Then</a></li>
        		<li class="tab col s3" ><a href="#that">That</a></li>
      		</ul>
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
    			<div class="col s4" style="padding-top: 20px">
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
     				<div class="col s4" style="padding-top: 20px">
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
		<div class="col s4" style="padding-top: 20px">
						<div class="center">
							<img alt="if1" src="assets/user.jpg" style="width:50px;height: 40px;">
							<p class="center " style="font-weight: bold;font-size:large;">HelloWorld</p>
							<p class="center"><input name="group2" type="radio" value="o7" id="o7" /><label for="o7"></label></p>
						</div>
				</div>
		</div>
		
		<div class="center">
			<input	class="center btn sub" type="submit" value="Create">
		</div>
		
		</div>
		</div>
		</form>
		</div>
		</li>
		<li class="collection-item close" style="width: 80%;"><div class="collapsible-header">close</div><div class="collapsible-body"><p></p></div></li>
		</ul>
  </body>
<script type="text/javascript">
$(document).on("click",".op",function(){
	var op=$(this).data("op");
    var id=$(this).data("id");
    if(op!="run")
    {    	
    var $li=$(this).parent().parent().parent().parent();
    $li.css("backgroundColor", "#00BCD4");
			$.post("TaskOperate",{op:op,id:id},function(data,status){
				if(op=="delete"){
              $li.slideToggle(300, function() {
                $li.remove();
            });
               
            }
            if(op=="edit"){
               var list=$(".close");
               list.load("component/DynEdit.jsp",function(){
               $('.collapsible').collapsible();
               $('.collapsible').collapsible();
               $("main").animate({scrollTop:$(document).height()}, 'slow');
               });
               
            }
            else{
            	location.reload();
            }
				
		});
	}
	else {
		var duration = prompt("请输入运行时间(秒)", "");
		$.post("TaskOperate",{op:op,id:id,duration:duration},function(data,status){
			location.reload();
		});
	}
	
});
</script>
</html>
