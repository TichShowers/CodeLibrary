<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<jsp:include page="_head.jsp">
	<jsp:param name="title" value="Fragment ${model.fragment.title}" />
</jsp:include>

<body>

	<jsp:include page="_navbar.jsp">
		<jsp:param name="current" value="fragment" />
	</jsp:include>

	<main class="container">

	<h2>
		Fragment ${model.fragment.title} <a href="/CodeLib/do/fragment/new/"
			class="btn btn-sm btn-primary"><span
			class="glyphicon glyphicon-pencil"></span> Edit this fragment</a>
	</h2>

	<p>Written in ${model.language.name}</p>

	<section style="margin: 10px 0;">
		<code> ${model.fragment.code} </code>
	</section>

	<ul class="list-group col-xs-12">
		<c:forEach var="comment" items="${model.comments}">
			<li class="list-group-item"><c:if
					test="${!empty admin && admin}">
					<a
						href="/CodeLib/do/comment/delete/${model.fragment.id}/${comment.id}"
						class="btn btn-xs btn-danger" style="float: right;">delete</a>
				</c:if>

				<p class="text-muted">
					<img src="${ comment.user.avatar }" style="height: 50px;" /> ${ comment.user.name }
					said on
					<fmt:formatDate value="${comment.at}" pattern="EEEE, dd MMMM yyyy" />
					:
				</p>
				<p>${comment.what}</p></li>
		</c:forEach>
		<li class="list-group-item">
			<form action="/CodeLib/do/comment/new/${model.fragment.id}"
				method=post class="form">
				<div class="form-group">
					<label for="what" class="sr-only">Comment</label>
					<textarea name="what" cols=80 rows=10 class="form-control"
						placeholder="Write a comment here..."></textarea>
				</div>

				<input class="btn btn-primary" type="submit" value="Save" class="">
			</form>
		</li>
	</ul>



	</main>
</body>
</html>