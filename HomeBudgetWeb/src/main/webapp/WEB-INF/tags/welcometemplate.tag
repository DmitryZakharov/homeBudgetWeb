<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
  <head>
  <head>
    <meta charset="utf-8">
    <title>Home Budget</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
    <style type="text/css">
      /* Sticky footer styles
      -------------------------------------------------- */
      html,
      body {
        height: 100%;
        /* The html and body elements cannot have any padding or margin. */
      }

      /* Wrapper for page content to push down footer */
      #wrap {
        min-height: 100%;
        height: auto !important;
        height: 100%;
        /* Negative indent footer by it's height */
        margin: 0 auto -30px;
      }

      /* Set the fixed height of the footer here */
      #push,
      #footer {
        height: 30px;
      }
      #footer {
        background-color: #f5f5f5;
      }

      /* Lastly, apply responsive CSS fixes as necessary */
      @media (max-width: 1200px) {
        #footer {
          margin-left: -5px;
          margin-right: -5px;
          padding-left: 5px;
          padding-right: 5px;
        }
      }

      /* Custom page CSS
      -------------------------------------------------- */
      /* Not required for template or sticky footer method. */

      .container {
        width: auto;
        max-width: 1200px;
      }
      .container .credit {
        margin: 5px 0;
      }

    </style>
    <link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="bootstrap/js/html5shiv.js"></script>
    <![endif]-->

   </head>

  <body>
      <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" href="#">Home Bedgeet</a>
          <div class="nav-collapse collapse">
            <ul class="nav">
              <li class="active"><a href="#">Home</a></li>
              <li><a href="#about">About</a></li>
              <li><a href="#contact">Contact</a></li>
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li><a href="#">Action</a></li>
                  <li><a href="#">Another action</a></li>
                  <li><a href="#">Something else here</a></li>
                  <li class="divider"></li>
                  <li class="nav-header">Nav header</li>
                  <li><a href="#">Separated link</a></li>
                  <li><a href="#">One more separated link</a></li>
                </ul>
                <li>
                <a href="registration.html">Register</a></li>
            </ul>
              	<form class="navbar-form pull-right" name='f' action="<c:url value='j_spring_security_check' />" method='POST'>
          	  		<input class="span2" type="text" name='j_username'  placeholder="Email/Username">
         		  		<input class="span2" type="password" name='j_password' placeholder="Password">
                		<label class="checkbox span pull-right" ><input id="remember_me" name="_spring_security_remember_me" type="checkbox" class="text-center" value="remember-me"/>
                			<font color="white">remember me</font>
                		</label>
            			<button type="submit" class="btn">Sign in</button>
         		</form>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>
  <div id=wrap>
    <div class="row-fluid">
<div class="span12"></div>
</div>
<div class="row-fluid">
<div class="span12"></div>
</div>
    <div id="body">
      <jsp:doBody/>
    </div>
    <div id="push"></div>
    </div>
    <div id="footer">
      <div class="container">
        <p class="muted credit">&copy; DIMI 2013</p>
      </div>
      </div>
  </body>
</html>