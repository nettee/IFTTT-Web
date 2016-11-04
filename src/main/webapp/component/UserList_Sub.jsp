<%@page import="model.data.Admin"%>
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
            	<input class="mdl-textfield__input"type="text" name="An">
              	<label class="mdl-textfield__label">公告</label>
        </div>
        <a class="btn sendAn">Send</a>
        </div>
	</li>
		<%
			List<User> userlist=null;
			Admin admin=null;
			if(subId==1) admin=new Admin(subuser);
			for (User u : admin.getUserList()){
		%>
		<li class="collection-item avatar">
			<div class="collapsible-header ">
				<i class="material-icons circle">account_circle</i>
				ID:<%=u.getId()%>&nbsp;&nbsp;用户名:<%=u.getName()%>			 
			</div>
			<div class="collapsible-body"><div>
			<a type="button" class="btn sendMessage" style="float: right;" data-id=<%=u.getId()%>>Send Message</a>
			<a type="button" class="btn levelChange" style="float: right;" data-id=<%=u.getId()%>>Level Change</a>
			<div>余额:<%=u.getBalance() %></div>
			<div>积分:<%=u.getScore() %></div>
			<div>等级:<%=u.getLevel() %></div>
			<div></div>
		
			</div>
			</div>
		</li> 
		<%
			}
		%>
		
	</ul>
	<script type="text/javascript">
		$(document).on("click",".sendAn",function(){
			var content=$("[name='An']").val();
			$.post("send",{content:content},function(){
			location.reload();
			});
		});
		$(document).on("click",".levelChange",function(){
			var id=$(this).data("id");
			var level = prompt("修改等级为", "");
			if(level!=null&&level!="")
			$.post("send",{level:level,id:id},function(){
			location.reload();
			});
		});
		$(document).on("click",".sendMessage",function(){
			var id=$(this).data("id");
			var content = prompt("私信内容", "");
			if(content!=null&&content!="")
			$.post("send",{content:content,id:id},function(){
			location.reload();
			});
		});
	</script>
</body>