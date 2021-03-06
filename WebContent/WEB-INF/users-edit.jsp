<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<jsp:include page="_head.jsp">
	<jsp:param name="title" value="Users" />
</jsp:include>
<body>
	<jsp:include page="_navbar.jsp">
		<jsp:param name="current" value="user" />
	</jsp:include>
	<main class="container">
	
	<c:if test="${ empty model}">
		<h2>Create New User</h2>
	</c:if> 
	<c:if test="${!empty model}">
		<h2>Edit User ${model.name}</h2>
	</c:if>

	<form action="/CodeLib/do/user/save/${model.id}" method=post
		class="form">
		<input type="hidden" name="id" value="${model.id}">
		<div class="form-group">
			<label for="name">Name</label> <input type="text" name="name"
				value="${model.name}" class="form-control">
		</div>
		<div class="form-group">
			<label for="name">Username</label> <input type="text" name="username"
				value="${model.username}" class="form-control">
		</div>
		<div class="form-group">
			<label for="name">Email</label> <input type="email" name="email"
				value="${model.email}" class="form-control">
		</div>
		<c:if test="${ empty model}">
			<div class="form-group">
				<label for="name">Password</label> <input type="password"
					name="password" value="" class="form-control">
			</div>
		</c:if>
		<div class="form-group">
			<input type="submit" value="Save" class="btn btn-primary"> <a
				class="btn btn-default" href="/CodeLib/do/user">Cancel</a>
		</div>
	</form>
	</main>
</body>
</html>