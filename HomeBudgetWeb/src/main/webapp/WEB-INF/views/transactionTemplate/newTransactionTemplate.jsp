<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:maintemplate>
   <jsp:body>
      <div class="container">
         <form:form method="POST" commandName="transactionTemplate" action="transactionTemplates/new.html">
            <table>

               <tr>
                  <td>new transaction template</td>
               </tr>
               <tr>	
                  <td>
                     <form:label path="comment">Transaction Template Comment:</form:label>
                     <form:input path="comment" />
                     <font color="red"><form:errors path="comment" /></font>
                  </td>
               </tr>
               <tr>	
                  <td>
                     <form:label path="type">Type:</form:label>
                     <form:select path="type" items="${transactionTypeList}" />
                     <font color="red"><form:errors path="type" /></font>
                  </td>
               </tr>
               <tr>
                  <td>
                     <form:label path="category">Category:</form:label>
                     <form:select name="category" path="category">
                        <c:forEach items="${categoryList}" var="category">
                     <option value="${category.name}">${category.name}</option>
                  </c:forEach>
               </form:select>
               <font color="red"><form:errors path="category" /></font>
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
                  <td colspan="3"><input type="submit" value="save transaction template" /></td>
               </tr>
            </table>
         </form:form>
      </div> <!-- /container -->
   </jsp:body>
</t:maintemplate>