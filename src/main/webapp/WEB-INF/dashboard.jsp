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
		<link rel="stylesheet" type="text/css" href="/css/style.css">
	</head>

	<body>
		<div class="container">
			<div class="row header">
				<div class="col-md-12">
					<h1>Dashboard</h1>
					<h3>Welcome ${currentUser.firstName}
						<c:forEach items="${currentUser.roles}" var="role">
							(${role.name})
						</c:forEach>
					</h3>
					<h4>Last login: <fmt:formatDate pattern="MMMM dd, yyyy hh:mm a" value="${currentUser.updatedAt}"/></h4>
					<br>
					<br>
					<br>

					
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<h4>Users:</h4>
					<table class="table">
						<thead>
							<tr>
								<th>Name</th>
								<th>Email</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${users}" var="user">
								<tr>
									<td>${user.firstName} ${user.lastName}</td>
									<td>${user.email}</td>
									<%! String admin = "ROLE_ADMIN"; %>
									<c:forEach items="${user.roles}" var="role">
										<c:choose>
											<c:when test="${role.name == 'ROLE_ADMIN'}">
												<td><p>Admin</p></td>
											</c:when>
											<c:otherwise>
												<td><a href="#">Delete</a> | <a href="#">Make-Admin</a> ${role.name}</td>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12 footer">
					<br>
					<br>
					<form id="logoutForm" method="POST" action="/logout">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<input type="submit" class="btn btn-default" value="Logout!" />
					</form>
				</div>
			</div>
		</div>
	</body>
</html>