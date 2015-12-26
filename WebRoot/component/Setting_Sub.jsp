<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<title>Sub page Setting of User Home</title>
<%@include file="Data_Sub.jsp" %> 
<link href="css/materialize.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.11.3.min.js" > </script>
<script src="./js/materialize.js"></script>
</head>
<style>
.task_background {
	background: url('') center/cover;
}
</style>
  <div class="card-panel grey lighten-5 z-depth-1">
          <div class="row valign-wrapper">
            <div class="col s2">
              <img src="assets/user.jpg" alt="" class="circle responsive-img"> <!-- notice the "circle" class -->
            </div>
            <div class="col s10">
              <span class="black-text">
              	<h4>Welcome user <a href="?page=Setting"><jsp:getProperty name="subuser" property="name"/></a>!</h4>
              	<span>等级:<%=subuser.getLevel()%> 积分:<%=subuser.getScore() %> </span>
              	<span>余额:<a href="?page=Purchase" class="tooltipped mdl-color-text--pink" data-tooltip="充值"><jsp:getProperty name="subuser" property="balance"/></a></span>
              </span>
            </div>
          </div>
        </div>
        <div class="card-panel grey lighten-5 z-depth-1">
        <div class="row valign-wrapper">
            <div class="col s2">
            change password
            </div>
            
       <div class="col s10">
        <div class="col s6"><input type="password" name="changed"></div>
        <a   class="btn center changePassword">change</a>
      </div>
      </div>
        </div>
        <script type="text/javascript">
		$(document).on("click",".changePassword",function(){
			var password=$("[name='changed']").val();
			if(password.length<6)
				alert("password too short");
			else{
				$.post("send",{password:password},function(){
					location.reload();
				});
			}
		});
		</script>