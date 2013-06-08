<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page isELIgnored="false"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:maintemplate>
   <jsp:body>
      <div class="container">
         <h1>List Of Categories for: <a href="<c:url value="categories/${name}.html" />">${name}</a></h1>
         <table title="List Of Categories" class="table table-striped table-condensed">
            <tr>
               <th>Category Name</th>
               <th>Category Parent</th>
               <th>Category Owner</th>
               <th></th>
            </tr>
            <script lang="javascript">
                 function SubmitForm(formname) {
                     document.forms[formname]
                        .submit();
                 }
            </script>
            <c:forEach items="${categoryList}" var="category">
               <tr>
                  <td>${category.name}</td>
                  <td>${category.parent.name}</td>
                  <td>${category.ownerMetadata.userDetails.username}</td>
                  <td>
                     <a href="<c:url value="categories/${category.name}.html" />"><i class="icon-edit"/></a>
                     
                     <form:form name="delete_${category.name}" method="delete" action="categories/${category.name}.html">

                        <i class="icon-trash" onclick="SubmitForm('delete_${category.name}')"></i>
                     </form:form>
                  </td>
               </tr>
            </c:forEach>
            </table>
      <a href="<c:url value="categories/new.html" />">create new category</a>
      </div> <!-- /container -->
   </jsp:body>
</t:maintemplate>