package mvc.responses;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Page implements ActionResult {
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
		RequestDispatcher aDispatcher = request.getRequestDispatcher("/WEB-INF/" + viewName);
		aDispatcher.forward(request, response);
	}
}
