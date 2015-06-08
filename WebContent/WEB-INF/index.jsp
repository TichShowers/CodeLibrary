<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="_head.jsp">
	<jsp:param name="title" value="Code Library" />
</jsp:include>

<body>
	<jsp:include page="_navbar.jsp">
		<jsp:param name="current" value="" />
	</jsp:include>

	<main class="container">
	<h2>Code Library</h2>
	<c:if test="${! empty model }">
		<div class"row">
			<div class="avatar-100 col-md-3">
				<img src="${ model.avatar }" />
			</div>
			<div class="col-md-9">
				<h3>Welcome back ${ model.name }</h3>
			</div>
		</div>
	</c:if> 
	</main>
</body>
</html>