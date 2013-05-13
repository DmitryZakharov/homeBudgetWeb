<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>List Of Accounts</h1>
	<table title="List Of Accounts">
		<c:forEach items="${accounts}" var="account">
			<tr>
				<td>${account.accountName}</td>
				<td>${account.dateOfCreation}</td>
				<td>${account.owner.userName}</td>
				<td>${account.startingBalance}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>