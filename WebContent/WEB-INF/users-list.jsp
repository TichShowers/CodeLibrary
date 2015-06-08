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
		<jsp:param name="current" value="user" />
	</jsp:include>

	<main class="container">
	<h2>
		Users <a href="/CodeLib/do/user/new/" class="btn btn-primary"><span
			class="glyphicon glyphicon-plus"></span> New user</a>
	</h2>

	<div class="container"></div>

	<table class="table table-hover" id="users-table">
		<thead>
			<tr>
				<th>Image</th>
				<th>Name</th>
				<th>Username</th>
				<th>Email</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="R" items="${model}">
				<tr>
					<td><c:if test="${! empty  R.avatar }">
							<img src="${R.avatar}" class="img-inline">
						</c:if> <c:if test="${ empty R.avatar }">
							<i class="glyphicon glyphicon-picture img-inline"></i>
						</c:if></td>
					<td>${R.name}</td>
					<td>${R.username}</td>
					<td>${R.email}</td>
					<td>
						<div class="btn-group btn-group-xs" role="group">
							<a href="/CodeLib/do/user/edit/${R.id}" class="btn btn-primary">edit</a>
							<a href="/CodeLib/do/user/password/${R.id}"
								class="btn btn-warning">change password</a> <a
								href="/CodeLib/do/user/delete/${R.id}" class="btn btn-danger">delete</a>
							<c:if test="${ !R.admin }">
								<a href="/CodeLib/do/user/promote/${R.id}"
									class="btn btn-default">promote</a>
							</c:if>
							<c:if test="${ R.admin }">
								<a href="/CodeLib/do/user/demote/${R.id}" class="btn btn-info">demote</a>
							</c:if>
						</div>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</main>
</body>
</html>