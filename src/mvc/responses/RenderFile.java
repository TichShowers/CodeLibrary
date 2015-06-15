package mvc.responses;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Colin Bundervoet
 *
 */
public abstract class RenderFile implements ActionResult {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setCharacterEncoding("utf-8");
		
		String login = (String) request.getSession().getAttribute("login.username");
		String name = (String) request.getSession().getAttribute("login.name");
		String admin = (String) request.getSession().getAttribute("login.admin");
		
		if (login != null && name != null && admin != null) {
			System.out.println("Login found for " + login);
			request.setAttribute("username", login);
			request.setAttribute("name", name);
			request.setAttribute("admin", admin.equals("true"));
		}
		
	}

}
