<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<header class="container">

	<div class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button class="btn btn-success navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="glyphicon glyphicon-menu-hamburger"></span>
				</button>

				<div id="logo">
					<a href='/CodeLib'><h4>Code Library</h4></a>
				</div>
			</div>

			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<c:if test="${! empty name}">
						<li class="nav"><a>Welcome back, ${name}!</a></li>
					</c:if>
					<li class="nav ${(empty param.current) ? 'active' : ''}"><a
						href="/CodeLib/do">Home</a></li>
					<li class="nav ${(param.current == 'language') ? 'active' : ''}"><a
						href="/CodeLib/do/language">Languages</a></li>
					<li class="nav ${(param.current == 'fragment') ? 'active' : ''}"><a
						href="/CodeLib/do/fragment">Fragments</a></li>
					<c:if test="${!empty admin && admin}">
						<li class="nav ${(param.current == 'user') ? 'active' : ''}"><a
							href="/CodeLib/do/user">Users</a></li>
					</c:if>
					<c:if test="${! empty username}">
						<li class="nav ${(param.current == 'setting') ? 'active' : ''}"><a
							href="/CodeLib/do/settings">Settings</a></li>
					</c:if>
					<li class="nav ${(param.current == 'login') ? 'active' : ''}"><c:if
							test="${empty username}">
							<a href="/CodeLib/do/login">Log in</a>
						</c:if> <c:if test="${! empty username}">
							<a href="/CodeLib/do/logout">Log out</a>
						</c:if></li>

				</ul>
				<div></div>
			</div>
</header>