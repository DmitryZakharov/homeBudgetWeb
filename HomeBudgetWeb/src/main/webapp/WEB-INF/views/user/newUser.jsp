<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:maintemplate>
   <jsp:body>
      <div class="container">
         <form:form commandName="userDetails">
            <table>
               <tr>
                  <td>USER INFORMATION</td>
                  <td><form:input path="fname" /></td>
                  <td><form:input path="sname" /></td>
                  <td><form:input path="birthday" /></td>
               </tr>
               <tr>
                  <td>
                     <div class="upload_form_cont">


                        <div class="fileupload">
                           <input  type="file"  id="file" name="userPic" onchange="fileSelected();"/>

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
                  <td colspan="3"><input type="submit" value="register" /></td>
               </tr>
            </table>
         </form:form>
      </div> <!-- /container -->
   </jsp:body>
</t:maintemplate>