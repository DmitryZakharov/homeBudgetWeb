<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add New Account</title>
</head>
<body>
	simple page
	<form:form method="POST" commandName="account" action="create.html">
		<table>
			<tr>
				<td>new account information</td>
				<td>
					<form:label path="accountName">Account name:</form:label>
					<form:input path="accountName" />
					<font color="red"><form:errors path="accountName" /></font>
				</td>
				</tr>
				<tr>
				<td>
					<form:label path="dateOfCreation">Date of Creation:</form:label>
					<form:input path="dateOfCreation" />
					<font color="red"><form:errors path="dateOfCreation" /></font>
				</td>
				</tr>
				<tr>
				<td>
					<form:label path="startingBalance">Starting Balance:</form:label>
					<form:input path="startingBalance" />					
					<font color="red"><form:errors path="startingBalance" /></font>
				</td>
				</tr>
			<tr>
				<td colspan="3"><input type="submit" value="add account" /></td>
			</tr>
		</table>

	</form:form>
</body>
</html>