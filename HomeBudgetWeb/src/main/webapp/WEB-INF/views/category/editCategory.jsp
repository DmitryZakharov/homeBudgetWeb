<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:maintemplate>
   <jsp:body>
      <div class="container">
         <form:form method="put" commandName="category" action="/categories">
            <form:input type="hidden" name="_method" value="put" path="" />
            <form:input type="hidden" name="id" path="id" />
            <table>
               <tr>
               <tr>
                  <td>Category Details</td>
               </tr>
               <tr>	
                  <td>
                     <form:label path="name">Category name</form:label>
                     <form:input path="name" />
                     <font color="red"><form:errors path="name" /></font>
                  </td>
               </tr>
               <tr>	
                  <td>
                     <form:label path="parent">Parent</form:label>
                     <form:select path="parent" items="${categoryList}"  />
                     <font color="red"><form:errors path="parent" /></font>
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