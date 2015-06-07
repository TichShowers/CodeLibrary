package mvc.filtering;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mvc.responses.ActionResult;
import mvc.responses.Redirect;

public class BasicAuthenticationFilter implements IAccessFilter {

	@Override
	public boolean hasAccess(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String path = (request.getPathInfo() == null)? "/" : request.getPathInfo();
		String login = (String) session.getAttribute("login");
		
		if(login == null)
		{
			session.setAttribute("stored.url", path);
		}
		
		return login != null;
	}

	@Override
	public ActionResult redirect() {
		return new Redirect("login");
	}

	@Override
	public void initialize(Connection connection) {

	}

}
