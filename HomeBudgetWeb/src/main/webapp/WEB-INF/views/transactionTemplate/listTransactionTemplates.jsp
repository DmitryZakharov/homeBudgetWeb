<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page isELIgnored="false"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:maintemplate>
    <jsp:body>
      <div class="container">
         <h1>List Of Transaction Templates</h1>
         <table title="List Of Transaction Templates" class="table table-striped table-condensed">
            <tr>
               <th></th>
               <th>Transaction Comment</th>
               <th>Transaction Type</th>
               <th>Transaction Category</th>
               <th>Amount</th>
               <th></th>
            </tr>
            <script lang="javascript">
													function SubmitForm(
															formname) {
														document.forms[formname]
																.submit();
													}
												</script>
            <c:forEach items="${transactionTemplateList}" var="transactionTemplate">
               <tr>

                  <td><span class="badge"><a
                                href="<c:url value="transactionTemplates/${transactionTemplate.id}.html" />">${transactionTemplate.id}</a></span></td>
                  <td>${transactionTemplate.comment}</td>
                  <td>${transactionTemplate.type}</td>
                  <td>${transactionTemplate.category.name}</td>
                  <td>${transactionTemplate.amount}</td>
                  <td>
                     <a href="<c:url value="transactionTemplates/${transactionTemplate.id}.html" />"><i
                                class="icon-edit" /></a>
                     
                     <form:form name="delete_${transactionTemplate.id}" method="delete"
                                action="transactionTemplates/${transactionTemplate.id}.html">

                        <i class="icon-trash" onclick="SubmitForm('delete_${transactionTemplate.id}')"></i>
                     </form:form>
                  </td>
               </tr>
            </c:forEach>
            </table>
           
      <a href="<c:url value="transactionTemplates/new.html" />">create new transactionTemplate</a>
      </div> <!-- /container -->
   </jsp:body>
</t:maintemplate>