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
public class Redirect implements ActionResult {

	private String url;
	
	public Redirect(String url)
	{
		this.url = url;
	}
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + request.getServletPath() + "/" +  url);
	}
	
}
