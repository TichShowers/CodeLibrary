<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="_head.jsp">
	<jsp:param name="title" value="Fragments - List" />
</jsp:include>

<body>
	<jsp:include page="_navbar.jsp">
		<jsp:param name="current" value="login" />
	</jsp:include>

	<main class="container">
	<h2>Please log in</h2>
	<c:if test="${! empty model }">
		<div class="alert alert-danger" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			${model}
		</div>
	</c:if>
	<form action="/CodeLib/do/login" method=post class="form">
		<div class="form-group">
			<label for="name">Username</label> <input type="text" name="username"
				value="" class="form-control">
		</div>
		<div class="form-group">
			<label for="name">Password</label> <input type="password"
				name="password" value="" class="form-control">
		</div>
		<input class="btn btn-primary" type="submit" value="Login">
	</form>
	</main>

</body>
</html>