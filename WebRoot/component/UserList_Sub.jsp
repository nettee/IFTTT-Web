<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<title>Sub page UserList of Admin Home</title>
<link href="css/materialize.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.11.3.min.js" > </script>
<script src="./js/materialize.js"></script>
</head>
<%@include file="Data_Sub.jsp" %>
<%if(subId!=1) response.sendRedirect("dashboard.jsp"); %>