package mvc.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;

import mvc.responses.*;

/**
 * 
 * @author Colin Bundervoet
 *
 */
public abstract class MvcController {
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	protected ServletContext context;
	private Connection connection; 
	
	public final ActionResult construct(HttpServletRequest request, HttpServletResponse response, ServletContext context, Connection connection, String action, Map<String, String> parameters)
	{
		this.request = request;
		this.response = response;
		this.connection = connection;
		this.context = context;
		
		this.session = request.getSession();
		
		this.response.setCharacterEncoding("UTF-8");	
		
		initialize(connection);
		
		return handle(action, parameters);
	}
	
	public abstract ActionResult handle(String action, Map<String, String> parameters);
	
	public void initialize(Connection connection){ }
	
	public String getParam(String theName, String theDefault) {
		String val = request.getParameter(theName);
		
		return (val == null) ? theDefault : val;
	}
	
	public int getParam(String theName, int theDefault) {
		String val = this.request.getParameter(theName);
		
		if (val == null) return theDefault;
		
		try { 
			return Integer.parseInt(val);
		} catch (NumberFormatException e) {
			return theDefault;
		}	
	}

	public long getParam(String theName, long theDefault) {
		String val = this.request.getParameter(theName);
		
		if (val == null) return theDefault;
		
		try { 
			return Long.parseLong(val);
		} catch (NumberFormatException e) {
			return theDefault;
		}	
	}

	public double getParam(String theName, double theDefault) {
		String val = this.request.getParameter(theName);
		
		if (val == null) return theDefault;
		
		try { 
			return Double.parseDouble(val);
		} catch (NumberFormatException e) {
			return theDefault;
		}	
	}
	
	public Date getParam(String theName, Date theDefault) {
		String val = this.request.getParameter(theName);
		
		if (val == null) return theDefault;
		
        SimpleDateFormat aDDMMYYYY = new SimpleDateFormat("dd-MM-yyyy");
        try {
        	return aDDMMYYYY.parse(val);
        } catch (Exception e) {
        	return theDefault;
        }
	}
	
	/* database connection */
	public Connection getConnection() {
		return this.connection;
	}
	
	/* store in session for JSP */
	public void setVar(String theName, Object theObject) {
		request.setAttribute(theName, theObject);
	}
	
	public <T> ActionResult view(String name, T model)
	{
		return new View<T>(name, model);
	}
	
	public ActionResult redirect(String url)
	{
		return new Redirect(url);
	}
	
	public ActionResult view(String name)
	{
		return new Page(name);
	}
	
	public ActionResult content(String content)
	{
		return new Content(content);
	}
	
	public ActionResult content(String content, String type)
	{
		return new Content(content, type);
	}
	
	public ActionResult error(int error)
	{
		return new ErrorResponse(error);
	}
}
