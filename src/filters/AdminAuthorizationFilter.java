package filters;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import persistence.UserDao;
import models.User;
import mvc.filtering.IAccessFilter;
import mvc.responses.ActionResult;
import mvc.responses.ErrorResponse;

public class AdminAuthorizationFilter implements IAccessFilter{

	private UserDao userDao;
	
	@Override
	public boolean hasAccess(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		String login = (String) session.getAttribute("login");
		
		List<User> users = userDao.search("username", login);
		
		if(!users.isEmpty())
		{
			User user = users.get(0);
			return user.isAdmin();
		}
		
		return false;
	}

	@Override
	public ActionResult redirect() {
		return new ErrorResponse(403);
	}

	@Override
	public void initialize(Connection connection) {
		userDao = new UserDao(connection);
	}

}
