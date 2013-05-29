<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:maintemplate>
    <jsp:body>
        <div class="container">
            <form:form commandName="userDetails">
                <table>
                    <tr>
                        <td>USER INFORMATION</td>
                        <td><form:input path="fname" /></td>
                        <td><form:input path="sname" /></td>
                        <td><form:input path="birthday" /></td>
                    </tr>
                    <tr>
                        <td colspan="3"><input type="submit" value="register" /></td>
                    </tr>
                </table>
            </form:form>
        </div> <!-- /container -->
    </jsp:body>
</t:maintemplate>