<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<jsp:include page="_head.jsp">
	<jsp:param name="title" value="Settings - Upload avatar" />
</jsp:include>
<body>
	<jsp:include page="_navbar.jsp">
		<jsp:param name="current" value="settings" />
	</jsp:include>
	<main class="container">
	<h2>Upload Avatar</h2>

	<div class="row">
		<jsp:include page="_settings-nav.jsp">
			<jsp:param name="current" value="avatar" />
		</jsp:include>
		<div class="col-md-9">
			<form method=post class="form" enctype="multipart/form-data">
				<div class="form-group">
					<label for="name">Upload avatar</label> <input type="file"
						name="avatar" class="form-control">
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