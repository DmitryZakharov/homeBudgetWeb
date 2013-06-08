<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:welcometemplate>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span10 offset3">
                <div class="bs-docs-example">
                    <form:form class="form-horizontal" method="Post" action="registration.html"
                        commandName="userDetails">
                        <legend>Sign Up</legend>
                        <div class="control-group">
                            <label class="control-label">User Name</label>
                            <div class="controls">
                                <div class="input-prepend">
                                    <span class="add-on"><i class="icon-user"></i></span>
                                    <form:input type="text" class="input-xlarge" id="uname" name="uname"
                                        placeholder="User Name" path="username" />
                                </div>
                                <div class="text">
                                    <form:errors class="text-error" path="username" />
                                </div>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">First Name</label>
                            <div class="controls">
                                <div class="input-prepend">
                                    <span class="add-on"><i class="icon-user"></i></span>
                                    <form:input type="text" class="input-xlarge" id="fname" name="fname"
                                        placeholder="First Name" path="fname" />
                                </div>
                                <div class="text">
                                    <form:errors class="text-error" path="fname" />
                                </div>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Last Name</label>
                            <div class="controls">
                                <div class="input-prepend">
                                    <span class="add-on"><i class="icon-user"></i></span>
                                    <form:input type="text" class="input-xlarge" id="lname" name="lname"
                                        placeholder="Last Name" path="sname" />
                                </div>
                                <div class="text">
                                    <form:errors class="text-error" path="sname" />
                                </div>
                            </div>
                        </div>
                        
                         <div class="control-group">
                            <label class="control-label">Main Currency</label>
                            <div class="controls">
                                <div class="input-prepend">
                                    <span class="add-on"><i class="icon-globe"></i></span>
                                    <form:select name="currency" class="input-xlarge" path="metadata.currency">
                                        <c:forEach items="${currencyList}" var="currency">
                                             <option value="${currency}">${currency}</option>
                                        </c:forEach>
                                    </form:select>
                                    <font color="red"><form:errors path="metadata.currency" /></font>
                                </div>
                                <div class="text">
                                    <form:errors class="text-error" path="sname" />
                                </div>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">Birthday</label>
                            <div class="controls">
                                <div class="input-prepend">
                                    <span class="add-on"><i class="icon-gift"></i></span>
                                    <form:input type="text" class="input-xlarge" id="datepicker" name="birthday"
                                        placeholder="Date of birth" path="birthday" />
                                </div>
                                <div class="text">
                                    <form:errors class="text-error" path="birthday" />
                                </div>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Email</label>
                            <div class="controls">
                                <div class="input-prepend">
                                    <span class="add-on"><i class="icon-envelope"></i></span>
                                    <form:input type="email" class="input-xlarge" id="email" name="email"
                                        placeholder="E-mail" path="email" />
                                </div>
                                <div class="text">
                                    <form:errors class="text-error" path="email" />
                                </div>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Password</label>
                            <div class="controls">
                                <div class="input-prepend">
                                    <span class="add-on"><i class="icon-lock"></i></span>
                                    <form:password id="passwd" class="input-xlarge" name="passwd" placeholder="Password"
                                        path="password" />
                                </div>
                                <div class="text">
                                    <form:errors class="text-error" path="password" />
                                </div>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Confirm Password</label>
                            <div class="controls">
                                <div class="input-prepend">
                                    <span class="add-on"><i class="icon-lock"></i></span>

                                    <form:password id="conpasswd" class="input-xlarge" name="conpasswd"
                                        placeholder="Confirm" path="confpassword" />
                                </div>
                                <div class="text">
                                    <form:errors class="text-error" path="confpassword" />
                                </div>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label"></label>
                            <div class="controls">
                                <button type="submit" class="btn btn-success">register me</button>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</t:welcometemplate>