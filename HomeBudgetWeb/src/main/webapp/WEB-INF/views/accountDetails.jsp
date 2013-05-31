<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:maintemplate>
    <jsp:body>
        <div class="container">
            <form:form method="put" commandName="account" action="accounts.html">
              <form:input type="hidden" name="_method" value="put" path="" />
              <form:input type="hidden" name="id" path="id" />
                <table>
                    <tr>
                    
                    <tr>
                        <td>Account Details</td>
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
                            <form:input type="text" id="datepicker" path="dateOfCreation" />
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
                        <td>
                            <form:label path="currency">Currency:</form:label>
                            <form:select path="currency" items="${currencyList}"  />
                            <font color="red"><form:errors path="currency" /></font>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3"><input type="submit" value="update" /></td>
                    </tr>
                </table>
            </form:form>
        </div> <!-- /container -->
    </jsp:body>
</t:maintemplate>