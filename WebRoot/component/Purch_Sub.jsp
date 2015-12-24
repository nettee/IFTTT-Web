<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<title>Sub page Setting of User Home</title>
<%@include file="Data_Sub.jsp" %> 
<link href="css/materialize.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.11.3.min.js" > </script>
<script src="./js/materialize.js"></script>
</head>
<body>
	<div class="row">
        <div class="col s12 ">
          <div class="card blue-grey darken-1">
            <div class="card-content white-text">
              <span class="card-title">会员充值</span>
              <p>
              
              		当前余额：<%=subuser.getBalance()%>
              		
              </p>
            </div>
            <div class="card-action">
            <div class="col s6 mdl-textfield mdl-js-textfield">
            	<input class="mdl-textfield__input"type="text">
              	<label class="mdl-textfield__label center">输入要充值的金额</label>
            </div>
              
              	<a class="btn"> 支付</a>
            </div>
          </div>
        </div>
      </div>
</body>
</html>
