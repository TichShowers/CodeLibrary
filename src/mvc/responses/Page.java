package mvc.responses;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Colin Bundervoet
 *
 */
public class Page extends RenderFile implements ActionResult {
	private String viewName;
	
	public Page(String name)
	{
		this.viewName = name;
	}


	public String getViewName() {
		return viewName;
	}

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.handle(request, response);
		RequestDispatcher aDispatcher = request.getRequestDispatcher("/WEB-INF/" + viewName);
		aDispatcher.forward(request, response);
	}
}
