<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:maintemplate>
   <jsp:body>
      <div class="container">
         <div class="bs-docs-example">
            <form:form method="put" action="user.html"  commandName="userDetails" enctype="multipart/form-data">
               <form:input type="hidden" name="_method" value="put" path=""/>
               <h2 class="registration-header">User Details</h2>
               <table>

                  <!-- USER USERNAME -->
                  <tr>

                     <td><form:errors class="text-error" path="username" /></td>
                  </tr>
                  <tr>
                     <td><form:input type="text" class="input" placeholder="Username" path="username" /></td>
                  </tr>
                  <!-- USER NAME -->
                  <tr>
                     <td><form:errors class="text-error" path="fname" /> 
                        <form:errors class="text-error" path="sname" />
                     </td>
                  </tr>
                  <tr>
                     <td><form:input type="text" class="span4 well" placeholder="Name" path="fname" /> 
                        <form:input type="text" class="span4 well" placeholder="Surname" path="sname" />
                     </td>
                  </tr>
                  <tr>
                     <td><form:errors class="text-error" path="password" /> 
                        <form:errors class="text-error" path="confpassword" />
                     </td>
                  </tr>
                  <tr>
                     <td><form:password class="span4 well" placeholder="Password" path="password" /> 
                        <form:password class="span4 well" placeholder="Confirm" path="confpassword" />
                     </td>
                  </tr>
                  <tr>
                     <td><form:errors class="text-error" path="email" /> 
                     </td>
                  </tr>
                  <tr>
                     <td><form:input type="email" class="input" placeholder="E-mail" path="email" /> 
                     </td>
                  </tr>

                  <tr>
                     <td><form:errors class="text-error" path="birthday" /> 
                     </td>
                  </tr>
                  <tr>
                     <td><form:input type="text" id="datepicker" class="input" placeholder="Date of birth"
                                 path="birthday" />
                     </td>
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
                  <!-- USER SUBMISSION ACTION -->
                  <tr>
                     <td><input class="btn btn-primary registration-submit" type="submit" value="update" /></td>
                  </tr>
               </table>
            </form:form>
         </div>

      </div> <!-- /container -->
   </jsp:body>
</t:maintemplate>