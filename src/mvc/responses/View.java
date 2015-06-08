package mvc.responses;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class View<T> extends RenderFile implements ActionResult {
	private T model;
	private String viewName;
	
	public View(String name, T model)
	{
		this.model = model;
		this.viewName = name;
	}
	
	public T getModel() {
		return model;
	}

	public String getViewName() {
		return viewName;
	}

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.handle(request, response);
		request.setAttribute("model", model);
		RequestDispatcher aDispatcher = request.getRequestDispatcher("/WEB-INF/" + viewName);
		aDispatcher.forward(request, response);
	}
}