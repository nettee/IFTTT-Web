<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Sign in</title>
    <link rel="stylesheet" type="text/css" href="css/custom.css" />
    <link rel="stylesheet" href="./css/material.css">
    <script src="./js/material.js"></script>
    <link rel="stylesheet" href="./css/icon.css">
</head>
<style>
    .demo-layout-transparent {
        background: url('./assets/Google-Material-Design-Wallpaper.jpg') center / cover;
    }
    .demo-layout-transparent .mdl-layout__header,
    .demo-layout-transparent .mdl-layout__drawer-button {
        color: white;
    }
    button{
    	margin:10px;
    }
</style>


<body>
  <noscript>
    &lt;div style="font-family:Avenir,&amp;quot;Helvetica Neue&amp;quot;,Helvetica,Arial,sans-serif;position:absolute;top:50%;width:100%;margin-top:-90px;text-align:center"&gt;
      &lt;h1&gt;Sorry :(&lt;/h1&gt;
      &lt;h2&gt;GoSquared requires JavaScript to run.&lt;/h2&gt;
      &lt;p&gt;You must enable JavaScript in order to use GoSquared.&lt;/p&gt;
    &lt;/div&gt;
  </noscript>
  <div class="demo-layout-transparent mdl-layout mdl-js-layout">
  <%@include file="component/nav.jsp" %>
      <main class="mdl-layout__content">
          <div class="login">
              <div class="login-form">
                  <div class="image">
                      <div>
                          <img src="./assets/120_120.png" class="logo">
                      </div>
                  </div>
                  <form action="/login.do" method="POST" id="login">
                      <div class="err" style="display: none;"></div>
                      <label class="">
                          <span>Username</span>
                          <input type="username" id="u" autofocus="" novalidate="novalidate" spellcheck="false" autocomplete="username" name="username">
                      </label>
                      <label class="">
                          <span>Password</span>
                          <input type="password" id="p" autocomplete="current-password" name="password">
                      </label>
                      <button type="submit" class="mdl-button mdl-color--blue">
                          <span class="mdl-color-text--white">Sign in</span>
                      </button>
                      <div class="create">
                          <span>Don't have an account yet?</span>
                          <a href="/register.jsp">Create an account</a>
                      </div>
                  </form>
              </div>
          </div>
      </main>

  </div>

</body>
</html>