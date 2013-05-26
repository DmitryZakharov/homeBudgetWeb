<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>List of Users</title>
    </head>
    <body>
        <h1>LIST  OF USERS</h1>
        <table title="List Of Users">
            <c:forEach items="${userList}" var="user">
                <tr>
                    <td>${user.userName}</td>
                    <td>${user.userSurname}</td>
                    <td>${user.userBirthday}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>