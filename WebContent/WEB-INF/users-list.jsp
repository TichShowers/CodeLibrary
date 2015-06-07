<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="_head.jsp">
	<jsp:param name="title" value="Users - List" />
</jsp:include>

<body>
	<jsp:include page="_navbar.jsp">
		<jsp:param name="current" value="users" />
	</jsp:include>

	<main class="container">
	<h2>
		List of Users <a href="/CodeLib/do/user/new/" class="btn btn-primary"><span
			class="glyphicon glyphicon-plus"></span> New user</a>
	</h2>

	<div class="container"></div>

	<table class="table table-striped table-bordered table-hover">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Username</th>
			<th>Actions</th>
		</tr>
		<c:forEach var="R" items="${model}">
			<tr>
				<td>${R.id}</td>
				<td>${R.name}</td>
				<td>${R.username}</td>
				<td>
					<div class="btn-group btn-group-xs" role="group">
						<a href="/CodeLib/do/user/edit/${R.id}" class="btn btn-primary">edit</a>
						<a href="/CodeLib/do/user/password/${R.id}"
							class="btn btn-warning">change password</a> <a
							href="/CodeLib/do/user/delete/${R.id}" class="btn btn-danger">delete</a>
						<c:if test="${ !R.admin }">
							<a href="/CodeLib/do/user/promote/${R.id}" class="btn btn-default">promote</a>
						</c:if>
						<c:if test="${ R.admin }">
							<a href="/CodeLib/do/user/demote/${R.id}" class="btn btn-info">demote</a>
						</c:if>
					</div>
				</td>
			</tr>
		</c:forEach>
	</table>
	</main>
</body>
</html>