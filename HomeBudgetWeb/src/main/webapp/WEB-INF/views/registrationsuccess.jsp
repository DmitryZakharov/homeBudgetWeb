<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:welcometemplate>
    <jsp:body>
        <div class="container">
        <h3>Dear ${userDetails.userUsername}, You will shortly receive an email for confirmation.</h3>
        </div> <!-- /container -->
    </jsp:body>
</t:welcometemplate>