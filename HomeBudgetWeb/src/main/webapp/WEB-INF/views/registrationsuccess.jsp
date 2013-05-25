<%@ page contentType="text/html" pageEncoding="windows-1252" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Registration Success</title>
    </head>
    <body>
        <h3>Welcome Registration Successfully.</h3>
        <table>
            <tr>
                <td>User Name :</td>
                <td><core:out value="${userDetails.userName}" /></td>
            </tr>
            <tr>
                <td>Password :</td>
                <td><core:out value="${userDetails.password}" /></td>
                <td><a href="login.html">login</a></td>
            </tr>
        </table>
    </body>
</html>
