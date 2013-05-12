<%@page language="java" contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Registration</title>
    </head>
    <body>
        <form:form method="Post" action="registration.html"
                   commandName="userDetails">
            <table>
                <tr>
                    <td>Name:<FONT color="red"><form:errors
                            path="userName" /></FONT></td>
                </tr>
                <tr>
                    <td><form:input path="userName" /></td>
                </tr>
                <tr>
                    <td>Surname:<FONT color="red"><form:errors
                            path="userSurname" /></FONT></td>
                </tr>
                <tr>
                    <td><form:input path="userSurname" /></td>
                </tr>
                <tr>
                    <td>Password:<FONT color="red"><form:errors
                            path="password" /></FONT></td>
                </tr>
                <tr>
                    <td><form:password path="password" /></td>
                </tr>
                <tr>
                    <td>Email:<FONT color="red"><form:errors path="email" /></FONT></td>
                </tr>
                <tr>
                    <td><form:input path="email" /></td>
                </tr>

            </tr>
            <tr>
                <td>Date of birth:<FONT color="red"><form:errors path="userBirthday" /></FONT></td>
            </tr>
            <tr>
                <td><form:input path="userBirthday" /></td>
            </tr>

            <tr>
                <td><input type="submit" value="Submit" /></td>
            </tr>
        </table>

    </form:form>

</body>
</html>
