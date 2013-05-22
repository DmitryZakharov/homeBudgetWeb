<%@tag description="Main page Template" pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@attribute name="footer" fragment="true" %>
<html>
  <head>
    <meta charset="utf-8">
    <title>Bootstrap, from Twitter</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 60px;
        padding-bottom: 40px;
      }
      .sidebar-nav {
        padding: 9px 0;
      }

      @media (max-width: 980px) {
        /* Enable use of floated navbar text */
        .navbar-text.pull-right {
          float: none;
          padding-left: 5px;
          padding-right: 5px;
        }
      }
    </style>
    <link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="../assets/js/html5shiv.js"></script>
    <![endif]-->

  </head>
  
  <body>
      <div id="pageheader">
      <div class="navbar navbar-inverse navbar-fixed-top">
	      <div class="navbar-inner">
	        <div class="container-fluid">
	          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	          </button>
	          <a class="brand" href="#">Home Budget</a>
	          <div class="nav-collapse collapse">
	            <p class="navbar-text pull-right ">
	            <span style="padding:0px 10px;"><i onclick="location.href='/HomeBudgetWeb/j_spring_security_logout';"  class="icon-off icon-white"></i></span> 
	            	<a href="<c:url value="/j_spring_security_logout" />" > Logout</a> 
	            </p>
	            <p class="navbar-text pull-right">
	              Logged in as <a href="#" class="navbar-link"><security:authentication property="principal.username"/></a>
	            </p>
	            <ul class="nav">
	              <li class="active"><a href="#">Home</a></li>
	              <li><a href="#about">About</a></li>
	              <li><a href="#contact">Contact</a></li>
	            </ul>
	          </div><!--/.nav-collapse -->
	        </div>
	      </div>
      </div>
    </div>
    
    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span2">
          <div class="well sidebar-nav">
            <ul class="nav nav-list">
              <li class="nav-header">Manage Accounts</li>
              <li><a href="account/createAccount.html">add new account</a></li>
              <li class="nav-header">Plan expences</li>
              <li><a href="#">Link</a></li>
              <li class="nav-header">Budget Planing</li>
              <li><a href="#">Link</a></li>
            </ul>
          </div><!--/.well -->
          
          
          
        </div><!--/span-->
        <div class="span9">



        </div><!--/span-->
      </div><!--/row-->

      <hr>

    </div><!--/.fluid-container-->

  
  
  
  
 
    <div id="body">
      <jsp:doBody/>
    </div>
 
       <footer>
        	<p>&copy; DIMI 2013-2015</p>
      </footer>
    
  </body>
</html>
