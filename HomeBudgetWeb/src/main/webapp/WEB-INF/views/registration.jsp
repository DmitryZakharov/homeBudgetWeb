<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
    </head>
    <body>
       <jsp:include page="layout/default.jsp" />
        <form:form method="Post" action="registration.html"
                   commandName="userDetails">
            <table class="table table-striped">
                <!-- USER USERNAME -->
                <tr>
                    <td>Username:<FONT color="red"><form:errors
                                path="userUsername" /></FONT></td>
                </tr>
                <tr>
                    <td><form:input path="userUsername" /></td>
                </tr>
                <!-- USER NAME -->
                <tr>
                    <td>Name:<FONT color="red"><form:errors path="userName" /></FONT></td>
                </tr>
                <tr>
                    <td><form:input path="userName" /></td>
                </tr>
                <!-- USER SURNAME -->
                <tr>
                    <td>Surname:<FONT color="red"><form:errors
                                path="userSurname" /></FONT></td>
                </tr>
                <tr>
                    <td><form:input path="userSurname" /></td>
                </tr>
                <!-- USER PASSWORD -->
                <tr>
                    <td>Password:<FONT color="red"><form:errors
                                path="password" /></FONT></td>
                </tr>
                <tr>
                    <td><form:password path="password" /></td>
                </tr>
                <!-- USER EMAIL -->
                <tr>
                    <td>Email:<FONT color="red"><form:errors path="email" /></FONT></td>
                </tr>
                <tr>
                    <td><form:input path="email" /></td>
                </tr>
                <!-- USER BIRTHDAY -->
                <tr>
                    <td>Date of birth:<FONT color="red"><form:errors
                                path="userBirthday" /></FONT></td>
                </tr>
                <tr>
                    <td><form:input path="userBirthday" /></td>
                </tr>

                <!-- USER SUBMISSION ACTION -->
                <tr>
                    <td><input type="submit" value="Submit" /></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>


