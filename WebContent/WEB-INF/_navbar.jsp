<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<header class="container">
	<div class="navbar navbar-fixed-top navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<button class="btn btn-success navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="glyphicon glyphicon-menu-hamburger"></span>
				</button>

				<a href='/CodeLib' class="navbar-brand">Code Library</a>
			</div>

			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li class="${(empty param.current) ? 'active' : ''}"><a
						href="/CodeLib/do"><i class="glyphicon glyphicon-home"></i> Home</a></li>
					<li class="${(param.current == 'language') ? 'active' : ''}"><a
						href="/CodeLib/do/language"><i class="glyphicon glyphicon-book"></i> Languages</a></li>
					<li class="${(param.current == 'fragment') ? 'active' : ''}"><a
						href="/CodeLib/do/fragment"><i class="glyphicon glyphicon-leaf"></i> Fragments</a></li>
					<c:if test="${!empty admin && admin}">
						<li class="${(param.current == 'user') ? 'active' : ''}"><a
							href="/CodeLib/do/user"><i class="glyphicon glyphicon-user"></i> Users</a></li>
					</c:if>
				</ul>

				<ul class="nav navbar-nav navbar-right">
					<c:if test="${! empty username}">
						<li class="${(param.current == 'setting') ? 'active' : ''}"><a
							href="/CodeLib/do/settings"><i class="glyphicon glyphicon-cog"></i> Settings</a></li>
					</c:if>
					<li class="${(param.current == 'login') ? 'active' : ''}">
						<c:if test="${empty username}">
							<a href="/CodeLib/do/login"><i class="glyphicon glyphicon-log-in"></i> Log in</a>
						</c:if> 
						<c:if test="${! empty username}">
							<a href="/CodeLib/do/logout"><i class="glyphicon glyphicon-off"></i> Log out</a>
						</c:if>
					</li>
				</ul>

				<c:if test="${! empty name}">
					<p class="navbar-text navbar-right">Welcome back, ${name}!</p>
				</c:if>

			</div>
		</div>
	</div>
</header>