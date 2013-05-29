<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page isELIgnored="false"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:maintemplate>
    <jsp:body>
        <div class="container">
            <h1>List Of Accounts</h1>
            <table title="List Of Accounts" class="table table-striped table-condensed">
            <tr>
                <th>Account name</th>
                <th>Date of Creation</th>
                <th>Owner</th>
                <th>Starting Balance</th>
                </tr>
                <c:forEach items="${accountList}" var="account">
                    <tr>
                        <td><a href="<c:url value="accounts/${account.name}.html" />">${account.name}</a></td>
                        <td>${account.executionDate}</td>
                        <td>${account.owner.name}</td>
                        <td>${account.startingBalance}</td>
                    </tr>
                </c:forEach>
            </table>
            <a href="<c:url value="accounts/new" />">create new account</a>
        </div> <!-- /container -->
    </jsp:body>
</t:maintemplate>