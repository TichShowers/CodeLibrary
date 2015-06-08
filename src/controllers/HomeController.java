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
public class HomeController extends MvcController {

	private UserDao userDao;
	
	@Override
	public void initialize(Connection connection) {
		userDao = new UserDao(connection);
	}

	@Override
	public ActionResult handle(String action, Map<String, String> parameters) {
		switch(action){
			case "index" : return index();
			case "contact" : return contact();
			case "social" : return social(parameters.get("link"), parameters.get("username"));
			case "id": return id(getParam("id", 0));
		}
		return null;
	}
	
	public ActionResult index(){
		return view("index.jsp", giveLoggedInUser());
	}
	
	public ActionResult contact()
	{
		// Well this works
		return content("This is my details");
	}
	
	public ActionResult social(String link, String username)
	{
		return content("Social link for " + link + " is " + username);
	}
	
	public ActionResult id(int id)
	{
		return content("Your id is: " + id);
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
}
