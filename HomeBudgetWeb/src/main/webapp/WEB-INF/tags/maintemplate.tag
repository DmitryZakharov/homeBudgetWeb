<%@tag description="Main page Template" pageEncoding="UTF-8"%>
<%@attribute name="footer" fragment="true"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- base is used for links so that they can be resolved properly -->
<base
    href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />

<meta charset="utf-8">
<title>Home Budget</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- Le styles -->
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet">

<%--      <link href="<%=request.getContextPath()%>/styles/layout.css" rel="stylesheet"> --%>
<link href="styles/jquery-ui-1.10.3.custom.css" rel="stylesheet">
<script src="scripts/jquery-1.9.1.js"></script>
<script src="scripts/jquery-ui-1.10.3.custom.js"></script>
<script type="text/javascript" src="scripts/homebudget.js"></script>



<style type="text/css">
/* Sticky footer styles
            -------------------------------------------------- */
html,body {
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
#push,#footer {
	height: 30px;
}

#footer {
	background-color: #f5f5f5;
}

/* Lastly, apply responsive CSS fixes as necessary */
@media ( max-width : 1200px) {
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
    <!-- HEADER -->
    <div class="navbar navbar-inverse navbar-fixed-top">
        <div class="navbar-inner">
            <div class="container-fluid">
                <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                    <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
                </button>
                <a class="brand" href="#">Home Budget</a>
                <div class="nav-collapse collapse">
                    <p class="navbar-text pull-right ">
                        <span style="padding: 0px 10px;"><i
                            onclick="location.href = 'j_spring_security_logout';"
                            class="icon-off icon-white"></i></span> <a
                            href="<c:url value="j_spring_security_logout" />"> Logout</a>
                    </p>
                    <p class="navbar-text pull-right">
                        Logged in as <a href="#" class="navbar-link"><security:authentication
                                property="principal.username" /></a>
                    </p>
                    <ul class="nav">
                        <li class="active"><a href="#">Home</a></li>
                        <li><a href="#about">About</a></li>
                        <li><a href="#contact">Contact</a></li>
                    </ul>
                </div>
                <!--/.nav-collapse -->
            </div>
        </div>
    </div>
    <div id=wrap>
        <!-- shifter for the main page f rom top, so it's not covered by header -->
        <div class="row-fluid">
            <div class="span12"></div>
        </div>
        <div class="row-fluid">
            <div class="span12"></div>
        </div>
        <!-- end of the shifter -->

        <div class="container-fluid">
            <div class="row-fluid">
                <!-- NAVIGATION PANAL -->
                <div class="span2">
                    <div class="well sidebar-nav">
                        <ul class="nav nav-list">
                            <li class="nav-header">Manage Accounts</li>
                            <li><a href="account/createAccount.html">add new
                                    account</a></li>
                            <li class="nav-header">Plan expences</li>
                            <li><a href="#">Link</a></li>
                            <li class="nav-header">Budget Planing</li>
                            <li><a href="#">Link</a></li>
                            <li><a href="#">Link</a></li>
                            <li><a href="#">Link</a></li>
                            <li class="nav-header">Personal settings</li>
                            <li><a href="userProfile.html">Change account</a></li>
                        </ul>
                    </div>
                    <!--/.well -->
                </div>
                <!--/span-->
                <!-- END OF NAVIGATION PANAL -->
                <!-- MAIN FIELD -->
                <div class="span9">
                    <div class="bs-docs-example">
                        <div id="body">
                            <jsp:doBody />
                        </div>
                    </div>
                </div>
                <!-- END OF THE MAIN FIELD -->
            </div>
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