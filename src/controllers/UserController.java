package controllers;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import persistence.UserDao;
import models.User;
import mvc.controllers.ResourceController;
import mvc.responses.ActionResult;

public class UserController extends ResourceController {

	private UserDao dao;

	@Override
	public ActionResult handle(String action, Map<String, String> parameters) {
		ActionResult result = super.handle(action, parameters);
		if (result != null) {
			return result;
		}

		int id = parseId(parameters.get("id"));

		switch (action) {
		case "promote":
			return promote(id);
		case "demote":
			return demote(id);
		case "password":
			return changePassword(id);
		}
		return null;
	}

	public ActionResult changePassword(int id) {
		User user = dao.read(id);

		if (request.getMethod().equals("POST")) {
			String password = getParam("password", "");
			String confirm = getParam("confirm", "");

			if (password.equals(confirm)) {
				user.changePassword(password);
				dao.update(user);
			}

			return redirect("user");
		} else {
			return view("users-password.jsp", user);
		}

	}

	public ActionResult demote(int id) {
		User user = dao.read(id);
		user.setAdmin(false);
		dao.update(user);

		return redirect("user");
	}

	public ActionResult promote(int id) {
		User user = dao.read(id);
		user.setAdmin(true);
		dao.update(user);

		return redirect("user");
	}

	@Override
	public void initialize(Connection connection) {
		dao = new UserDao(connection);
	}

	@Override
	public ActionResult index() {
		List<User> users = dao.readAll();
		return view("users-list.jsp", users);
	}

	@Override
	public ActionResult show(int index) {
		// TODO Auto-generated method stub
		return redirect("user/edit/" + index);
	}

	@Override
	public ActionResult create() {
		// TODO Auto-generated method stub
		return view("users-edit.jsp");
	}

	@Override
	public ActionResult edit(int index) {
		User user = dao.read(index);
		return view("users-edit.jsp", user);
	}

	@Override
	public ActionResult delete(int index) {
		dao.remove(index);
		return redirect("user");
	}

	@Override
	public ActionResult save(int index) {
		User user = dao.read(index);

		user.setName(getParam("name", ""));
		user.setUsername(getParam("username", ""));

		dao.update(user);
		return redirect("user");
	}

	@Override
	public ActionResult save() {
		User user = new User(0, getParam("name", ""), getParam("username", ""),
				false);
		user.changePassword(getParam("password", ""));

		dao.insert(user);

		return redirect("user");
	}

}
