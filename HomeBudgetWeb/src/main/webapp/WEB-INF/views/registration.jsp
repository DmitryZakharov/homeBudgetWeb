<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
        <link href="bootstrap/css/bootstrap.css" rel="stylesheet">
        <link href="styles/layout.css" rel="stylesheet">
    </head>
    <body>


<div class="container">
        <form:form method="Post" action="registration.html"
                   commandName="userDetails">
            <h2 class="registration-header">Please Register Yourself</h2>
            <table class="center-table">
                <!-- USER USERNAME -->
                <tr>

                    <td><form:errors class="text-error" path="userUsername" /></td>
                </tr>
                <tr>
                    <td><form:input type="text" class="input" placeholder="Username" path="userUsername"/></td>
                </tr>
                <!-- USER NAME -->
                <tr>
                    <td><form:errors class="text-error" path="userName" /> 
                        <form:errors class="text-error" path="userSurname" />
                    </td>
                </tr>
                <tr>
                    <td><form:input type="text" class="input-small" placeholder="Name" path="userName"/> 
                        <form:input type="text" class="input-small" placeholder="Surname" path="userSurname"/>
                    </td>
                </tr>
                <tr>
                    <td><form:errors class="text-error" path="password" /> 
                        <form:errors class="text-error" path="confpassword" />
                    </td>
                </tr>
                <tr>
                    <td><form:password class="input-small" placeholder="Password" path="password"/> 
                        <form:password class="input-small" placeholder="Confirm" path="confpassword"/>
                    </td>
                </tr>
                <tr>
                    <td><form:errors class="text-error" path="email" /> 
                    </td>
                </tr>
                <tr>
                    <td><form:input type="email" class="input" placeholder="E-mail" path="email"/> 
                    </td>
                </tr>
                
                <tr>
                    <td><form:errors class="text-error" path="dateString" /> 
                    </td>
                </tr>
                <tr>
                    <td><form:input type="date" class="input" placeholder="Date of birth" path="dateString"/> 
                    </td>
                </tr>
                
              

                <!-- USER SUBMISSION ACTION -->
                <tr>
                    <td><input class="btn btn-primary registration-submit" type="submit" value="Submit" /></td>
                </tr>
            </table>
        </form:form>
</div>
    </body>
</html>


