package mvc.responses;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Content implements ActionResult {

	private String text;
	private String type;
	
	public Content(String text)
	{
		this(text, "text/html");
	}
	
	public Content(String text, String type)
	{
		this.text = text;
		this.type = type;
	}
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType(type);
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.println(text);
	}

}
