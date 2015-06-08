package controllers;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import persistence.UserDao;
import models.User;
import mvc.controllers.MvcController;
import mvc.responses.ActionResult;

/**
 * 
 * @author Colin Bundervoet
 *
 */
public class AuthController extends MvcController {

	private UserDao dao;

	@Override
	public void initialize(Connection connection) {
		dao = new UserDao(connection);
	}

	@Override
	public ActionResult handle(String action, Map<String, String> parameters) {
		switch (action) {
		case "login":
			if (request.getMethod().equals("POST")) {
				return postLogin();
			} else {
				return showlogin();
			}
		case "logout":
			return logout();
		}

		return null;
	}

	public ActionResult showlogin() {
		return view("login.jsp");
	}

	public ActionResult postLogin() {
		List<User> users = dao.search("username", getParam("username", ""));

		if (users.isEmpty()) {
			return view("login.jsp", "Wrong username or password");
		} else {
			User user = users.get(0);
			if (user.checkPassword(getParam("password", ""))) {
				session.setAttribute("login.username", user.getUsername());
				session.setAttribute("login.name", user.getName());
				session.setAttribute("login.admin", user.isAdmin() ? "true" : "");
				
				String path = (String) session.getAttribute("stored.url");

				removeSession("stored.url");
				if (path == null) {
					return redirect("");
				} else {
					return redirect(path);
				}
			} else {
				return view("login.jsp", "Wrong username or password");
			}
		}
	}

	public ActionResult logout() {
		removeSession("login.username");
		removeSession("login.name");
		removeSession("login.admin");
		
		return redirect("login");
	}

	private void removeSession(String string) {
		session.removeAttribute(string);
	}
}
