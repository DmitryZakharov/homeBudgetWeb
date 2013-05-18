<%@page import="org.homebudget.springmvc.PaginatedListAdapter"%>
<%@page import="org.displaytag.pagination.PaginatedList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<% request.setAttribute( "paginatedList", new PaginatedListAdapter() ); %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Le styles -->
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="styles/layout.css" rel="stylesheet">
<link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
	<h1>List Of Accounts</h1>
	<table title="List Of Accounts" class="table table-striped table-condensed">
		<c:forEach items="${accounts}" var="account">
			<tr>
				<td>${account.accountName}</td>
				<td>${account.dateOfCreation}</td>
				<td>${account.owner.userName}</td>
				<td>${account.startingBalance}</td>
			</tr>
		</c:forEach>
	</table>


 <display:table  name="paginatedList"  >
    <display:column property="accountName" title="AccountName" sortable="true" style="width:300px "/>
</display:table>



	<a href="<c:url value="/account/createAccount.html" />">create new
		account</a>
		
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