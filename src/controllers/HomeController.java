package controllers;

import java.util.Map;

import mvc.controllers.MvcController;
import mvc.responses.ActionResult;

public class HomeController extends MvcController {

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
		return view("index.jsp");
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
}
