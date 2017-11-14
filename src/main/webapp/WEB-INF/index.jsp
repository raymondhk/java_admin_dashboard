<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Login and Registration</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<!-- <link rel="stylesheet" type="text/css" href="/css/style.css"> -->
	</head>

	<body>
		<div class="container">
			<br>
			<br>
			<br>
			<div class="row">
				<div class="col-md-12">
					<button type="button" class="btn btn-default" data-toggle="modal" data-target="#login">Login</button>
					<button type="button" class="btn btn-default" data-toggle="modal" data-target="#registration">Registration</button>
					<!-- Modal -->
					<div class="modal fade" id="login" role="dialog">
						<div class="modal-dialog">
						
						  <!-- Modal content-->
						  <div class="modal-content">
							<div class="modal-header" style="padding:35px 50px;">
							  <button type="button" class="close" data-dismiss="modal">&times;</button>
							  <h2 class="text-center"><span class="glyphicon glyphicon-lock"></span> Login</h2>
							</div>
							<div class="modal-body" style="padding:40px 50px;">
							  <form role="form" action="/login" method="post">
								<div class="form-group">
								  	<label for="email"><span class="glyphicon glyphicon-envelope"></span> Email</label>
								  	<input type="text" name="email" class="form-control" id="email" placeholder="Enter email">
								</div>
								<div class="form-group">
								  	<label for="psw"><span class="glyphicon glyphicon-eye-open"></span> Password</label>
								  	<input type="text" name="password" class="form-control" id="psw" placeholder="Enter password">
								</div>
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
								  	<button type="submit" class="btn btn-success btn-block"><span class="glyphicon glyphicon-off"></span> Login</button>
							  </form>
							</div>
							<div class="modal-footer">
							  <p>Not a member? <button class="btn btn-default" data-toggle="modal" data-target="#registration" data-dismiss="modal">Sign Up</button></p>
							</div>
							</div>
						</div>
					</div>
					<div id="registration" class="modal fade" role="dialog">
							<div class="modal-dialog">
	
								<!-- Registration Modal content-->
								<div class="modal-content">
										<div class="modal-header" style="padding:35px 50px;">
										  <button type="button" class="close" data-dismiss="modal">&times;</button>
										  <h2 class="text-center"><span class="glyphicon glyphicon-edit"></span> Registration</h2>
										</div>
										<div class="modal-body" style="padding:40px 50px;">
										  <form:form role="form" action="/registration" method="post" modelAttribute="user">
											<div class="form-group">
												  <form:label path="email"><span class="glyphicon glyphicon-envelope"></span> Email</form:label>
												  <form:input type="text" path="email" class="form-control" id="email" placeholder="Enter email"/>
											</div>
											<div class="form-group">
												<form:label path="firstName"><span class="glyphicon glyphicon-user"></span> First Name</form:label>
												<form:input type="text" path="firstName" class="form-control" id="firstName" placeholder="Enter first name"/>
											</div>
											<div class="form-group">
												<form:label path="lastName"><span class="glyphicon glyphicon-user"></span> Last Name</form:label>
												<form:input type="text" path="lastName" class="form-control" id="lastName" placeholder="Enter last name"/>
											</div>
											<div class="form-group">
												  <form:label path="password"><span class="glyphicon glyphicon-eye-open"></span> Password</form:label>
												  <form:input type="text" path="password" class="form-control" id="password" placeholder="Enter password"/>
											</div>
											<div class="form-group">
												<form:label path="passwordConfirmation"><span class="glyphicon glyphicon-eye-close"></span> Confirm Password</form:label>
												<form:input type="text" path="passwordConfirmation" class="form-control" id="passwordConfirmation" placeholder="Enter password confirmation"/>
											</div>
												
												  <form:button type="submit" class="btn btn-success btn-block"><span class="glyphicon glyphicon-off"></span> Sign Up</form:button>
										  </form:form>
										</div>
										<div class="modal-footer">
										  <p>Already a member? <button class="btn btn-default" data-toggle="modal" data-target="#login" data-dismiss="modal">Login</button></p>
										</div>
								</div>
	
							</div>
					</div>
					<p><form:errors path="user.*"/></p>
				</div>
			</div>
		</div>
	</body>
</html>