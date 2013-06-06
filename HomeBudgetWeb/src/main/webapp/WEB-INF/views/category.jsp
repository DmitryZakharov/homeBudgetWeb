<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:maintemplate>
    <jsp:body>
        <div class="container">
            <form:form method="POST" commandName="category" action="categories/new.html">
                <table>
                    <tr><tr>
                        <td>new category information</td>
                    </tr>
                    <tr>	
                        <td>
                            <form:label path="name">Category Name</form:label>
                            <form:input path="name" />
                            <font color="red"><form:errors path="name" /></font>
                        </td>
                    </tr>
                    <tr>	
                        <td>
                            <form:label path="parent">Parent:</form:label>
                              <form:select name="category" path="parent">
                                <c:forEach items="${categoryList}" var="parent">
                                    <option value="${parent.name}">${parent.name}</option>
                                </c:forEach>
                            </form:select>
                            <font color="red"><form:errors path="parent" /></font>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3"><input type="submit" value="add category"/></td>
                    </tr>
                </table>
            </form:form>
        </div> <!-- /container -->
    </jsp:body>
</t:maintemplate>