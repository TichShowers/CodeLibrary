<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="_head.jsp">
	<jsp:param name="title" value="Fragments - List" />
</jsp:include>

<body>
	<jsp:include page="_navbar.jsp">
		<jsp:param name="current" value="fragment" />
	</jsp:include>

	<main class="container">
	<h2>
		Fragments <a href="/CodeLib/do/fragment/new/"
			class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span>
			New fragment</a>
	</h2>

	<div class="container"></div>

	<table class="table table-hover">
		<thead>
			<tr>
				<th>id</th>
				<th>Title</th>
				<th>Created</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="R" items="${model}">
				<tr>
					<td>${R.id}</td>
					<td><a href="/CodeLib/do/fragment/show/${R.id}">${R.title}</</a></td>
					<td>${R.at}</td>
					<td>
						<div class="btn-group btn-group-xs" role="group">
							<a href="/CodeLib/do/fragment/edit/${R.id}"
								class="btn btn-primary">edit</a> <a
								href="/CodeLib/do/fragment/delete/${R.id}"
								class="btn btn-danger">delete</a>
						</div>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</main>
</body>
</html>