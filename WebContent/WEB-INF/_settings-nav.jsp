<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<ul class="col-md-3 nav nav-pills nav-stacked">
	<li class="${(param.current == 'index') ? 'active' : ''}"><a href="/CodeLib/do/settings">Main Settings</a></li>
	<li class="${(param.current == 'password') ? 'active' : ''}"><a href="/CodeLib/do/settings/password">Change Password</a></li>
	<li class="${(param.current == 'avatar') ? 'active' : ''}"><a href="/CodeLib/do/settings/avatar">Upload Avatar</a></li>
</ul>