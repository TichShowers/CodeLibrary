package controllers;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import persistence.UserDao;
import models.User;
import mvc.controllers.MvcController;
import mvc.responses.ActionResult;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

/**
 * 
 * @author Colin Bundervoet
 *
 */
public class SettingController extends MvcController {

	private UserDao dao;
	
	@Override
	public void initialize(Connection connection) {
		dao = new UserDao(connection);
	}

	@Override
	public ActionResult handle(String action, Map<String, String> parameters) {
		User user = giveLoggedInUser();
		
		if(user == null)
		{
			return redirect("");
		}
		
		switch(action)
		{
			case "index" : return index(user);
			case "password" : return password(user);
			case "avatar": return avatar(user);
		}
		
		return null;
	}
	
	public ActionResult avatar(User user) {
		if(request.getMethod().equals("POST"))
		{
			try {
		        List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
		        
		        System.out.println(items.size());
		        
		        if(!items.isEmpty() && !items.get(0).isFormField())
		        {
		        	FileItem avatarFile = items.get(0);
		        	if(isAcceptedUploadType(avatarFile.getContentType()))
		        	{
		        		String savePath = context.getRealPath("images");
		        		String[] split = avatarFile.getName().split("\\.");
			        	String saveName = user.getUsername() + "." + split[split.length - 1];
			        	
			        	File file = new File(savePath, saveName);
			        	
			        	try {
							avatarFile.write(file);
						} catch (Exception e) {
							return view("settings-avatar.jsp");
						}
			        	
			        	user.setAvatar(request.getContextPath() + "/images/" + saveName);
			        	
			        	dao.update(user);
		        	}
		        }
		    } catch (FileUploadException e) {
		    	return view("settings-avatar.jsp");
		    }
			
			return view("settings-avatar.jsp");
		}
		else
		{
			return view("settings-avatar.jsp");
		}
	}

	public ActionResult password(User user) {
		if(request.getMethod().equals("POST"))
		{
			String old = getParam("old", "");
			if(user.checkPassword(old))
			{
				String password = getParam("new", "");
				String confirm = getParam("confirm", "");
				if(password.equals(confirm))
				{
					user.changePassword(password);
					dao.update(user);
					return view("settings-password.jsp", createMessageResponse("Your password has been changed!", "success"));
				}
				else
				{
					return view("settings-password.jsp", createMessageResponse("Your new password and confirm password do not match!", "danger"));
				}
			}
			else
			{
				return view("settings-password.jsp", createMessageResponse("Your current password is incorrect!", "danger"));
			}
			
		}
		else
		{
			return view("settings-password.jsp");
		}
	}

	public ActionResult index(User user) {
		if(request.getMethod().equals("POST"))
		{
			user.setName(getParam("name", ""));
			user.setEmail(getParam("email", ""));
			
			session.setAttribute("login.name", user.getName());
			
			dao.update(user);
			
			return view("settings-index.jsp", user);
		}
		else
		{
			return view("settings-index.jsp", user);
		}
	}
	
	private boolean isAcceptedUploadType(String contentType)
	{
		switch(contentType)
		{
		case "image/png": case "image/jpeg": case "image/gif": return true;
		default: return false;
		}
	}

	private User giveLoggedInUser()
	{
		List<User> list = dao.search("username", (String) session.getAttribute("login.username"));
		
		if(list.isEmpty())
		{
			return null;
		}
		return list.get(0);
	}
	
	private Map<String, String> createMessageResponse(String message, String severity)
	{
		Map<String, String> map = new HashMap<>();
		
		map.put("message", message);
		map.put("type", severity);
		
		return map;
	}
}
