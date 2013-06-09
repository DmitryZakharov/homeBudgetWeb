<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page isELIgnored="false"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:maintemplate>
    <jsp:body>
      <div class="container">
         <h1>List Of Transactions for: <a href="<c:url value="accounts/${name}.html" />">${name}</a>
            </h1>
         <table title="List Of Transactions" class="table table-striped table-condensed">
            <tr>
               <th></th>
               <th>Transaction Comment</th>
               <th>Transaction Type</th>
               <th>Transaction Category</th>
               <th>Date of Creation</th>
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
            <c:forEach items="${transactionList}" var="transaction">
               <tr>

                  <td><span class="badge"><a
                                href="<c:url value="accounts/${name}/transactions/${transaction.id}.html" />">${transaction.id}</a></span></td>
                  <td>${transaction.comment}</td>
                  <td>${transaction.type}</td>
                  <td>${transaction.category.name}</td>
                  <td>${transaction.executionDate}</td>
                  <td>${transaction.amount}</td>
                  <td>
                     <a href="<c:url value="accounts/${name}/transactions/${transaction.id}.html" />"><i
                                class="icon-edit" /></a>
                     
                     <form:form name="delete_${transaction.id}" method="delete"
                                action="accounts/${name}/transactions/${transaction.id}.html">

                        <i class="icon-trash" onclick="SubmitForm('delete_${transaction.id}')"></i>
                     </form:form>
                  </td>
               </tr>
            </c:forEach>
            </table>
            <form:form method="GET" action="accounts/${name}/transactions/">
             <h3>Select dates for filtering transactions</h3>
             <table>
               <tr>
                  <td>
                  <label>Starting date</label>
                     <input name="start" type="text" id="datepicker" />
                     <font color="red"><form:errors path="start" /></font>
                  </td>
                  <td>
                  <label>End date</label>
                     <input name="end" type="text" id="datepicker" />
                     <font color="red"><form:errors path="end" /></font>
                  </td>
                  <td colspan="3"><input type="submit" value="filter" /></td>
               </tr>
            </table>
       </form:form>
      <a href="<c:url value="accounts/${name}/transactions/new.html" />">create new transaction</a>
      </div> <!-- /container -->
   </jsp:body>
</t:maintemplate>