<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Sign in &middot; Twitter Bootstrap</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- Le styles -->
<link href="resources/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="resources/styles/layout.css" rel="stylesheet">
<link href="resources/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="resources/bootstrap/js/html5shiv.js"></script>
    <![endif]-->

<!-- Fav and touch icons -->
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="resources/bootstrap/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="resources/bootstrap/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="resources/bootstrap/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="resources/bootstrap/ico/apple-touch-icon-57-precomposed.png">
<link rel="shortcut icon" href="resources/bootstrap/ico/favicon.png">
</head>

<body>

	<div class="container">

<%-- 		<c:if test="${not empty error}"> --%>
<!-- 			<div class="errorblock"> -->
<!-- 				Your login attempt was not successful, try again.<br /> Caused : -->
<%-- 				${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message} --%>
<!-- 			</div> -->
<%-- 		</c:if> --%>

		<form class="form-signin" name='f' action="<c:url value='j_spring_security_check' />" method='POST'>
			<h2 class="form-signin-heading">Please sign in</h2>
			<input type="text" name='j_username' class="input-block-level"		placeholder="User name"> 
			<input type='password'	name='j_password' class="input-block-level" placeholder="Password">
			<label class="checkbox"> <input type="checkbox"	value="remember-me">Remember me</label>
			<button class="btn btn-large btn-primary" type="submit">Sign in</button>
			 <a href="registration.html">Register</a>
		</form>

	</div>
	<!-- /container -->

	<!-- Le javascript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="resources/bootstrap/js/jquery.js"></script>
	<script src="resources/bootstrap/js/bootstrap-transition.js"></script>
	<script src="resources/bootstrap/js/bootstrap-alert.js"></script>
	<script src="resources/bootstrap/js/bootstrap-modal.js"></script>
	<script src="resources/bootstrap/js/bootstrap-dropdown.js"></script>
	<script src="resources/bootstrap/js/bootstrap-scrollspy.js"></script>
	<script src="resources/bootstrap/js/bootstrap-tab.js"></script>
	<script src="resources/bootstrap/js/bootstrap-tooltip.js"></script>
	<script src="resources/bootstrap/js/bootstrap-popover.js"></script>
	<script src="resources/bootstrap/js/bootstrap-button.js"></script>
	<script src="resources/bootstrap/js/bootstrap-collapse.js"></script>
	<script src="resources/bootstrap/js/bootstrap-carousel.js"></script>
	<script src="resources/bootstrap/js/bootstrap-typeahead.js"></script>

</body>
</html>