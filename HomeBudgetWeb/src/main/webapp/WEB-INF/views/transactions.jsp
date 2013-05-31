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
               <th></th>
               <th>Transaction Comment</th>
               <th>Transaction Type</th>
               <th>Transaction Category</th>
               <th>Date of Creation</th>
               <th>Account</th>
               <th>Amount</th>
               <th></th>
            </tr>
            <c:forEach items="${transactionList}" var="transaction">
               <tr>

                  <td><span class="badge"><a href="<c:url value="accounts/${transaction.parent.name}/transactions/${transaction.id}.html" />">${transaction.id}</a></span></td>
                  <td>${transaction.comment}</td>
                  <td>${transaction.type}</td>
                  <td>${transaction.category.name}</td>
                  <td>${transaction.executionDate}</td>
                  <td>${transaction.parent.name}</td>
                  <td>${transaction.amount}</td>
                  <td>
                     <a href="<c:url value="accounts/${transaction.parent.name}/transactions.html" />"><i class="icon-edit"/></a>

                     <form:form name="delete" method="delete" action="accounts/${transaction.parent.name}/transactions/${transaction.id}.html">
                        <script lang="javascript">
                           function SubmitForm() {
                              document.forms['delete']
                                      .submit();
                           }
                        </script>
                        <i class="icon-trash" onclick="SubmitForm()"></i>
                     </form:form>
                  </td>
               </tr>
         </table>
         <a href="<c:url value="accounts/${transaction.parent.name}/transactions/new.html" />">create new transaction</a>
      </c:forEach>
      </div> <!-- /container -->
   </jsp:body>
</t:maintemplate>