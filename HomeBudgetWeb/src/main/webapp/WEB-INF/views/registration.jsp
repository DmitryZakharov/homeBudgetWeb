<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:welcometemplate>

<jsp:body>
    <div class="container-fluid">
    <div class="row-fluid">
    <div class="span2">
    <!--Sidebar content-->
    </div>
    <div class="span10">
  <form:form method="Post" action="registration.html"
                   commandName="userDetails">
            <h2 class="registration-header">Please Register Yourself</h2>
            <table class="offset3">
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
                    <td><form:input type="text" class="span4 well" placeholder="Name" path="userName"/> 
                        <form:input type="text" class="span4 well" placeholder="Surname" path="userSurname"/>
                    </td>
                </tr>
                <tr>
                    <td><form:errors class="text-error" path="password" /> 
                        <form:errors class="text-error" path="confpassword" />
                    </td>
                </tr>
                <tr>
                    <td><form:password class="span4 well" placeholder="Password" path="password"/> 
                        <form:password class="span4 well" placeholder="Confirm" path="confpassword"/>
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
                    <td><input class="btn btn-primary registration-submit" type="submit" value="register me" /></td>
                </tr>
            </table>
        </form:form>
    </div>
    </div>
    </div>
    
</jsp:body>

</t:welcometemplate>