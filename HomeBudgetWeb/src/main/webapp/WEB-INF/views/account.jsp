<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:maintemplate>
    <jsp:body>
        <div class="container">
            <form:form method="POST" commandName="account" action="accounts.html">
                <table>
                    <tr><tr>
                        <td>new account information</td>
                    </tr>
                    <tr>	
                        <td>
                            <form:label path="name">Account name:</form:label>
                            <form:input path="name" />
                            <font color="red"><form:errors path="name" /></font>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="dateOfCreation">Date of Creation:</form:label>
                            <form:input  type="text"  id="datepicker" path="dateOfCreation" />
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
        </div> <!-- /container -->
    </jsp:body>
</t:maintemplate>