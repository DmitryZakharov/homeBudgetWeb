<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
                                    <form:errors class="text-error" path="userUsername" />
                                    <form:input type="text" class="input-xlarge" id="uname" name="uname"
                                        placeholder="User Name" path="userUsername" />
                                </div>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">First Name</label>
                            <div class="controls">
                                <div class="input-prepend">
                                    <span class="add-on"><i class="icon-user"></i></span>
                                    <form:errors class="text-error" path="userName" />
                                    <form:input type="text" class="input-xlarge" id="fname" name="fname"
                                        placeholder="First Name" path="userName" />
                                </div>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Last Name</label>
                            <div class="controls">
                                <div class="input-prepend">
                                    <span class="add-on"><i class="icon-user"></i></span>
                                    <form:errors class="text-error" path="userSurname" />
                                    <form:input type="text" class="input-xlarge" id="lname" name="lname"
                                        placeholder="Last Name" path="userSurname" />
                                </div>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">Birthday</label>
                            <div class="controls">
                                <div class="input-prepend">
                                    <span class="add-on"><i class="icon-gift"></i></span>
                                    <form:errors class="text-error" path="userBirthday" />
                                    <form:input type="text" class="input-xlarge" id="datepicker" name="birthday"
                                        placeholder="Date of birth" path="userBirthday" />
                                </div>
                            </div>
                        </div>


                        <div class="control-group">
                            <label class="control-label">Email</label>
                            <div class="controls">
                                <div class="input-prepend">
                                    <span class="add-on"><i class="icon-envelope"></i></span>
                                    <form:errors class="text-error" path="email" />
                                    <form:input type="email" class="input-xlarge" id="email" name="email"
                                        placeholder="E-mail" path="email" />
                                </div>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Password</label>
                            <div class="controls">
                                <div class="input-prepend">
                                    <span class="add-on"><i class="icon-lock"></i></span>
                                    <form:errors class="text-error" path="password" />
                                    <form:password id="passwd" class="input-xlarge" name="passwd" placeholder="Password"
                                        path="password" />
                                </div>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Confirm Password</label>
                            <div class="controls">
                                <div class="input-prepend">
                                    <span class="add-on"><i class="icon-lock"></i></span>
                                    <form:errors class="text-error" path="confpassword" />
                                    <form:password id="conpasswd" class="input-xlarge" name="conpasswd"
                                        placeholder="Confirm" path="confpassword" />
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