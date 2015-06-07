import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Map;

import common.ControllerDirectory;
import common.RouteConfig;
import mvc.AbstractRouter;
import mvc.Route;
import mvc.Tuple;
import mvc.controllers.ControllerFactory;
import mvc.controllers.MvcController;
import mvc.filtering.IAccessFilter;
import mvc.responses.ActionResult;
import mvc.responses.ErrorResponse;

public class DefaultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AbstractRouter router;
	private ControllerFactory controllers;

	public DefaultServlet() {
		super();

		controllers = new ControllerFactory(new ControllerDirectory());
		router = new RouteConfig();
	}

	public Connection getConnection() {
		try {
			ServletContext aContext = this.getServletContext();
			String aDBUsername = aContext.getInitParameter("gDBUser"); // root
																		// or
																		// mysql
			String aDBPassword = aContext.getInitParameter("gDBPassword"); // xxx
			String gDBUrl = aContext.getInitParameter("gDBUrl");

			System.out.println("Router.getConnection: with user " + aDBUsername
					+ " to " + gDBUrl);

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection aCon = DriverManager.getConnection(gDBUrl, aDBUsername,
					aDBPassword);
			System.out
					.println("Router.getConnection: Connected to the database");
			return aCon;

		} catch (Exception e) {
			System.out
					.println("Router.getConnection: Could not get a connection.");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		handle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		handle(request, response);
	}

	private void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = (request.getPathInfo() == null) ? "/" : request
				.getPathInfo();

		if (!path.endsWith("/")) {
			path += "/";
		}

		Connection connection = getConnection();

		Tuple<Route, Map<String, String>> tuple = router.findRoute(path);

		if(tuple !=null)
		{
			Route route = tuple.left;

			String login = (String) request.getSession().getAttribute("login");

			if (login != null) {
				System.out.println("Login found for " + login);
				request.setAttribute("user", login);
			}
			
			List<IAccessFilter> filters = route.getFilters();

			ActionResult redirect = null;

			for (IAccessFilter filter : filters) {
				filter.initialize(connection);
				if (!filter.hasAccess(request)) {
					redirect = filter.redirect();
					redirect.handle(request, response);
					break;
				}
			}

			if (redirect == null) {
				try {
					MvcController controller = controllers.giveController(route
							.getController());

					ActionResult result = controller.construct(request, response,
							connection, route.getAction(), tuple.right);
					
					if(result == null)
					{
						result = new ErrorResponse(404);
					}
					
					result.handle(request, response);
					
				} catch (Exception e) {
					System.out.println(e.toString());
					response.sendError(404);
				}
			}
		}
		else
		{
			ActionResult result = new ErrorResponse(404);
			result.handle(request, response);
		}

	}
}
