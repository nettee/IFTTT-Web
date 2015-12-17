<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<title>Sub page Task of User Home</title> 
<link href="css/materialize.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.11.3.min.js" > </script>
<script src="./js/materialize.js"></script>

</head>
<style>
.task_background {
	background: url('') center/cover;
}
</style>
<div class="demo-card-wide">
	<ul class="collection" style="width: 80%">
	<li class="collection-item avatar"><i class="material-icons circle">view_headline</i><span class="title">Task1</span></li>
	<li class="collection-item avatar"><i class="material-icons circle">view_headline</i><span class="title">Task2</span></li>
	</ul>
</div>
 <ul class="collapsible" data-collapsible="accordion" style="width: 80%">
    <li>
      <div class="collapsible-header"><i class="material-icons">add</i>Add New Task</div>
      <div class="collapsible-body">
      <form>
      <div class="row">
    	<div class="col s12">
      		<ul class="tabs">
        		<li class="tab col s3 disabled"><a href="#test1">If</a></li>
        		<li class="tab col s3"><a href="#this">This</a></li>
        		<li class="tab col s3 disabled"><a href="#test1">Then</a></li>
        		<li class="tab col s3"><a href="#that">That</a></li>
      		</ul>
    	</div>
    	<div id="this" class="col s12">
    		<div class="row">
				<div class="col s4">
					<div class="center promo promo-example"><i class="material-icons">flash_on</i><p class="promo-caption">Speeds up development</p><p class="light center">We did most of the heavy lifting for you to provide a default stylings that incorporate our custom components.</p></div>
      			</div>
      			<div class="col s4">
      			</div>
      			<div class="col s4">
      			</div>
			</div>
		</div>
    	<div id="that" class="col s12">Test 3</div>
		</div>
		<input	class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect" type="submit" value="Create">
		</form>
		</div>
    </li>
    <li>
      <div class="collapsible-header"><i class="material-icons">close</i>Close</div>
      <div class="collapsible-body"></div>
    </li>
  </ul>
</html>
