<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:maintemplate>
    <jsp:body>
        <div class="container">
            <div class="bs-docs-example">
                <form:form method="PUT" action="user.html"  commandName="userDetails">
                <form:input type="hidden" name="_method" value="put" path=""/>
                    <h2 class="registration-header">User Details</h2>
                    <table>

                        <!-- USER USERNAME -->
                        <tr>

                            <td><form:errors class="text-error" path="username" /></td>
                        </tr>
                        <tr>
                            <td><form:input type="text" class="input" placeholder="Username" path="username" /></td>
                        </tr>
                        <!-- USER NAME -->
                        <tr>
                            <td><form:errors class="text-error" path="fname" /> 
                                <form:errors class="text-error" path="sname" />
                            </td>
                        </tr>
                        <tr>
                            <td><form:input type="text" class="span4 well" placeholder="Name" path="fname" /> 
                                <form:input type="text" class="span4 well" placeholder="Surname" path="sname" />
                            </td>
                        </tr>
                        <tr>
                            <td><form:errors class="text-error" path="password" /> 
                                <form:errors class="text-error" path="confpassword" />
                            </td>
                        </tr>
                        <tr>
                            <td><form:password class="span4 well" placeholder="Password" path="password" /> 
                                <form:password class="span4 well" placeholder="Confirm" path="confpassword" />
                            </td>
                        </tr>
                        <tr>
                            <td><form:errors class="text-error" path="email" /> 
                            </td>
                        </tr>
                        <tr>
                            <td><form:input type="email" class="input" placeholder="E-mail" path="email" /> 
                            </td>
                        </tr>

                        <tr>
                            <td><form:errors class="text-error" path="birthday" /> 
                            </td>
                        </tr>
                        <tr>
                            <td><form:input type="text" id="datepicker" class="input" placeholder="Date of birth"
                                    path="birthday" />
                            </td>
                        </tr>
                        <!-- USER SUBMISSION ACTION -->
                        <tr>
                            <td><input class="btn btn-primary registration-submit" type="submit" value="update" /></td>
                        </tr>
                    </table>
                </form:form>
            </div>

        </div> <!-- /container -->
    </jsp:body>
</t:maintemplate>