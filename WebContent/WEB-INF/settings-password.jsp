<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<jsp:include page="_head.jsp">
	<jsp:param name="title" value="Settings - Password" />
</jsp:include>
<body>
	<jsp:include page="_navbar.jsp">
		<jsp:param name="current" value="settings" />
	</jsp:include>
	<main class="container">
	<h2>Change Password</h2>

	<c:if test="${! empty model }">
		<div class="alert alert-${model.type} }" role="alert">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			${model.message}
		</div>
	</c:if>

	<div class="row">
		<jsp:include page="_settings-nav.jsp">
			<jsp:param name="current" value="password" />
		</jsp:include>
		<div class="col-md-9">
			<form method=post class="form">
				<div class="form-group">
					<label for="name">Old Password</label> <input type="password"
						name="old" class="form-control">
				</div>
				<div class="form-group">
					<label for="name">New Password</label> <input type="password"
						name="new" class="form-control">
				</div>
				<div class="form-group">
					<label for="name">Confirm Password</label> <input type="password"
						name="confirm" class="form-control">
				</div>

				<div class="form-group">
					<input type="submit" value="Save" class="btn btn-primary">
				</div>
			</form>
		</div>
	</div>
	</main>
</body>
</html>