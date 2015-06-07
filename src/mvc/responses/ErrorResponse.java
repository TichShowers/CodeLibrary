package mvc.responses;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorResponse implements ActionResult {

	private int errorCode;
	
	public ErrorResponse(int errorCode) {
		super();
		this.errorCode = errorCode;
	}

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendError(errorCode);
	}

}
