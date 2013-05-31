<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page isELIgnored="false"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:maintemplate>
    <jsp:body>
        <div class="container">
            <h1>List Of Transactions</h1>
            <table title="List Of Transactions" class="table table-striped table-condensed">
            <tr>
                <th>Transaction Comment</th>
                <th>Transaction Type</th>
                <th>Transaction Category</th>
                <th>Date of Creation</th>
                <th>Account</th>
                <th>Amount</th>
                </tr>
        <%--        <c:forEach items="${transactionList}" var="transaction">
                    <tr>
                        <td><a href="<c:url value="transactions/${transaction.comment}.html" />">${transaction.comment}</a></td>
                        
                        <td>${transaction.type}</td>
                        <td>${transaction.category.name}</td>
                        <td>${transaction.executionDate}</td>
                        <td>${transaction.parent.name}</td>
                        <td>${transaction.amount}</td>
                    </tr>
                </c:forEach>
        
        --%>
            </table>
            <a href="<c:url value="transactions/new" />">create new transaction</a>
        </div> <!-- /container -->
    </jsp:body>
</t:maintemplate>