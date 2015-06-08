package controllers;

import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import persistence.CommentDao;
import persistence.UserDao;
import models.Comment;
import models.User;
import mvc.controllers.MvcController;
import mvc.responses.ActionResult;

/**
 * 
 * @author Colin Bundervoet
 *
 */
public class CommentController extends MvcController {

	private CommentDao dao;
	private UserDao userDao;
	
	@Override
	public void initialize(Connection connection) {
		dao = new CommentDao(connection);
		userDao = new UserDao(connection);
	}

	@Override
	public ActionResult handle(String action, Map<String, String> parameters) {
		if(action.equals("new"))
		{
			User user = giveLoggedInUser();
			Comment comment = new Comment(0, parseIntegerParameter(parameters.get("fragment")), user, new Date(), getParam("what", ""));
			
			dao.insert(comment);
			return redirect("fragment/show/" + parameters.get("fragment"));
		}
		if(action.equals("delete"))
		{
			dao.remove(parseIntegerParameter(parameters.get("id")));
			return redirect("fragment/show/" + parameters.get("fragment"));
		}
		return null;
	}

	
	private User giveLoggedInUser()
	{
		List<User> list = userDao.search("username", (String) session.getAttribute("login.username"));
		
		if(list.isEmpty())
		{
			return null;
		}
		return list.get(0);
	}
	
	private int parseIntegerParameter(String parameter)
	{
		if(parameter == null)
		{
			return 0;
		}
		
		try{
			return Integer.parseInt(parameter);
		}
		catch(NumberFormatException e)
		{
			return 0;
		}
	}
}
