<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/material.css">
<script src="./js/material.js"></script>
<script src="./js/jquery-1.11.3.min.js"></script>
<link rel="stylesheet" href="./css/icon.css">
</head>
<style>
.demo-layout-transparent {
	background: url('./assets/Android-L-Material-Design-Wallpapers-5.png')
		center/cover;
}
.demo-card-wide.mdl-card {
	width: 512px;
	text-align: center;
	margin: 0 auto;
}

.demo-card-wide>.mdl-card__title {
	color: #fff;
	height: 176px;
}

.demo-card-wide>.mdl-card__menu {
	color: #fff;
}
.warning{
	 color: rgb(239,83,80) !important;
}
</style>
<body>
	<noscript>&lt;div
		style="font-family:Avenir,&amp;quot;Helvetica
		Neue&amp;quot;,Helvetica,Arial,sans-serif;position:absolute;top:50%;width:100%;margin-top:-90px;text-align:center"&gt;
		&lt;h1&gt;Sorry :(&lt;/h1&gt; &lt;h2&gt;GoSquared requires JavaScript
		to run.&lt;/h2&gt; &lt;p&gt;You must enable JavaScript in order to use
		GoSquared.&lt;/p&gt; &lt;/div&gt;</noscript>

	<div class="demo-layout-transparent mdl-layout mdl-js-layout">
		<%@include file="component/nav.jsp"%>
		<main class="mdl-layout__content">
		<div class="demo-card-wide mdl-card mdl-shadow--2dp">
			<div class="mdl-card__title">
				<h2 class="mdl-card__title-text mdl-color-text--black">Welcome</h2>
			</div>
			<div class="mdl-card__supporting-text">
				<div class="warning" style="display: none"
					id="emailwarning">Please enter your email.</div>
				<div class="warning" style="display: none"
					id="passwordwarning">Opps! Password too short.</div>
				<div class="warning" style="display: none"
					id="confirmwarning">Opps! Confirm don't agree.</div>

				<form action="/dashboard.jsp" method="POST" id="register" onsubmit="return register_validate(this)">

					<div class="mdl-textfield mdl-js-textfield">
						<input class="mdl-textfield__input" type="username" id="u"
							autofocus="" spellcheck="false" autocomplete="username"
							name="username"> <label class="mdl-textfield__label">User
							Name</label>
					</div>
					<div class="mdl-textfield mdl-js-textfield">
						<input class="mdl-textfield__input" type="password" id="p"
							autocomplete="current-password" name="password"> <label
							class="mdl-textfield__label">Password</label>
					</div>
					<div class="mdl-textfield mdl-js-textfield">

						<input class="mdl-textfield__input" type="password" id="p2"
							name="confirmed_password"> <label
							class="mdl-textfield__label">Confirm Password</label>
					</div>

				</form>
			</div>
			<div class="mdl-card__actions mdl-card--border">
				<input
					class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect"
					type="submit" value="Get Started" form="register"> </input>

			</div>
		</div>
		</main>

	</div>
	<script>		
		function register_validate(thisform){
			/*clear all existing warnings*/
			clear();
			/*validate input params*/
			with (thisform){
				if (username.value.length == 0){
					$("#emailwarning").fadeIn();
					return false;
				} else if(password.value.length < 6){
					$("#passwordwarning").fadeIn();
					return false;
				} else if(confirmed_password.value != password.value){
					$("#confirmwarning").fadeIn();
					return false;
				} else {
					return true;
				}
			}
		}
		function clear(){
			$("#logwarning").fadeOut(0);
			$("#registerwarning").fadeOut(0);
			$("#emailwarning").fadeOut(0);
			$("#passwordwarning").fadeOut(0);
			$("#confirmwarning").fadeOut(0);
		}
	</script>
</body>
</html>