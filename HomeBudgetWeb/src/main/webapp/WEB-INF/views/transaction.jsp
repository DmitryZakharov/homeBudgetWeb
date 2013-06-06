<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:maintemplate>
   <jsp:body>
      <div class="container">
         <form:form method="POST" commandName="transaction" action="accounts/${name}/transactions/new.html" enctype="multipart/form-data">
            <table>
               <tr><tr>
                    <tr>
                    <tr>
                  <td>new transaction information</td>
               </tr>
               <tr>	
                  <td>
                     <form:label path="comment">Transaction Comment:</form:label>
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
                     <form:label path="executionDate">Date of Creation:</form:label>
                            <form:input type="text" id="datepicker" path="executionDate" />
                     <font color="red"><form:errors path="executionDate" /></font>
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
                  <td>
                     <div class="upload_form_cont">


                        <div class="fileupload">
                           <input  type="file"  id="file" name="attachment" onchange="fileSelected();"/>

                        </div>

                        <table>
                           <td>
                              <img id="preview"/>
                           </td>
                           <td>
                              <div id="fileinfo">
                                 <div id="filename"></div>
                                 <div id="filesize"></div>
                                 <div id="filetype"></div>
                                 <div id="filedim"></div>
                              </div>
                           </td>
                        </table>


                        <div id="error">You should select valid image files only!</div>
                        <div id="error2">An error occurred while uploading the file</div>
                        <div id="abort">The upload has been canceled by the user or the browser dropped the connection</div>
                        <div id="warnsize">Your file is very big. We can't accept it. Please select more small file</div>

                        <div id="progress_info">
                           <div id="progress"></div>
                           <div id="progress_percent">&nbsp;</div>
                           <div class="clear_both"></div>


                        </div>


                     </div>
                  </td>
                  </tr>
               <tr>
                  <td colspan="3"><input type="submit" value="add transaction" /></td>
               </tr>
            </table>
         </form:form>
      </div> <!-- /container -->
   </jsp:body>
</t:maintemplate>