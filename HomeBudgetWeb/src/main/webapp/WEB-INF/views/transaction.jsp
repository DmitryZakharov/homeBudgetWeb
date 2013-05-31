<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:maintemplate>
    <jsp:body>
        <div class="container">
            <form:form method="POST" commandName="transaction" action="transactions.html">
                <table>
                    <tr><tr>
                        <td>new transaction information</td>
                    </tr>
                    <tr>	
                        <td>
                            <form:label path="comment">Transaction Comment:</form:label>
                            <form:input path="comment" />
                            <font color="red"><form:errors path="comment" /></font>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="executionDate">Date of Creation:</form:label>
                            <form:input  type="text"  id="datepicker" path="executionDate" />
                            <font color="red"><form:errors path="executionDate" /></font>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="amount">Amount:</form:label>
                            <form:input path="amount" />					
                            <font color="red"><form:errors path="amount" /></font>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3"><input type="submit" value="add transaction" /></td>
                    </tr>
                </table>
            </form:form>
        </div> <!-- /container -->
    </jsp:body>
</t:maintemplate>