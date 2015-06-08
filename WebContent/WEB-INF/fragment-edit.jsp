<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="_head.jsp">
	<jsp:param name="title" value="Fragment - Edit" />
</jsp:include>

<body>

	<jsp:include page="_navbar.jsp">
		<jsp:param name="current" value="fragment" />
	</jsp:include>

	<main class="container">

	<c:if test="${ empty model}">
		<h2>Create New Fragment</h2>
	</c:if> 
	<c:if test="${!empty model}">
		<h2>Edit Fragment ${model.title}</h2>
	</c:if>

	<form action="/CodeLib/do/fragment/save/${model.fragment.id}"
		method=post class="form">
		<input type="hidden" name="id" value="${model.fragment.id}">
		<div class="form-group">
			<label for="title">Title</label> 
			<input type="text" name="title"
				value="${model.fragment.title}" class="form-control">
		</div>

		<div class="form-group">
			<label for="language">Language</label> 
			<select name="language"
				class="form-control">
				<option value=0>Select a language</option>
				<c:forEach var="L" items="${model.languages}">
					<option value="${L.id}"
						${(L.id == model.fragment.language) ? 'selected' : ''}>${L.name}</option>
				</c:forEach>
			</select>
		</div>

		<div class="form-group">
			<label for="code">Code</label>
			<textarea name="code" cols=80 rows=10 class="form-control">${model.fragment.code}</textarea>
		</div>

		<input class="btn btn-primary" type="submit" value="Save"> <a class="btn btn-default" href="/CodeLib/do/fragment">Cancel</a>
	</form>

	</main>
</body>
</html>