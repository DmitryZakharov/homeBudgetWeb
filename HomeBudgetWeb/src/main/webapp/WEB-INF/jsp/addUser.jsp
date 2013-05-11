<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add New User</title>
</head>
<body>
	<form:form commandName="userDetails">
		<table>
			<tr>
				<td>USER INFORMATION</td>
				<td><form:input path="userName" /></td>
				<td><form:input path="userSurname" /></td>
				<td><form:input path="userBirthday" /></td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit" value="register" /></td>
			</tr>
		</table>

	</form:form>
</body>
</html>