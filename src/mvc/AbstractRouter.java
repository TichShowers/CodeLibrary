package mvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mvc.filtering.BasicAuthenticationFilter;
import mvc.filtering.IAccessFilter;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public abstract class AbstractRouter {

	private Map<String, Route> routes;
	private IAccessFilter authFilter;

	public AbstractRouter() {
		this.routes = new HashMap<>();
		authFilter = new BasicAuthenticationFilter();
	}
	
	
	
	public final Tuple<Route, Map<String, String>> findRoute(String route) {
		configure();

		System.out.println("Route matched: " + route);

		for (String key : routes.keySet()) {
			String expression = key.replaceAll("\\{[A-Za-z0-9\\-]*\\}",
					"[A-Za-z0-9]+");

			if (route.matches(expression)) {
				String[] routeSplit = route.split("/");
				String[] keySplit = key.split("/");

				Map<String, String> parameters = new HashMap<>();

				for (int i = 0; i < routeSplit.length; i++) {
					if (keySplit[i].startsWith("{")) {
						expression = keySplit[i].replaceAll("\\{|\\}", "");

						parameters.put(expression, routeSplit[i]);
					}
				}

				return new Tuple<Route, Map<String, String>>(routes.get(key),
						parameters);
			}
		}

		System.out.println("No routes found! ");
		return null;
	}

	public abstract void configure();

	public final void route(String url, String controller, String action) {
		route(url, controller, action, false);
	}

	public final void route(String url, String controller, String action,
			boolean needsLogin) {
		if (needsLogin) {
			route(url, controller, action, authFilter);
		} else {
			route(url, controller, action, new ArrayList<IAccessFilter>());
		}
	}

	public final void route(String url, String controller, String action,
			IAccessFilter filter) {
		List<IAccessFilter> filters = new ArrayList<>();

		filters.add(filter);

		route(url, controller, action, filters);
	}

	public final void route(String url, String controller, String action,
			List<IAccessFilter> filters) {
		if (!url.startsWith("/")) {
			url = "/" + url;
		}

		if (!url.endsWith("/")) {
			url += "/";
		}

		routes.put(url, new Route(controller, action, filters));
	}

	public final void resource(String controller) {
		resource(controller, false);
	}

	public final void resource(String controller, List<IAccessFilter> filters) {
		route(controller + "/", controller, "index", filters);
		route(controller + "/new/", controller, "new", filters);
		route(controller + "/edit/{id}/", controller, "edit", filters);
		route(controller + "/delete/{id}/", controller, "delete", filters);
		route(controller + "/show/{id}/", controller, "show", filters);
		route(controller + "/save/{id}/", controller, "update", filters);
		route(controller + "/save/", controller, "store", filters);
	}

	public final void resource(String controller, boolean needsLogin) {
		if(needsLogin)
		{
			resource(controller, authFilter);
		}
		else
		{
			resource(controller, new ArrayList<IAccessFilter>());
		}
	}

	public final void resource(String controller, IAccessFilter filter) {
		List<IAccessFilter> filters = new ArrayList<>();

		filters.add(filter);

		resource(controller, filters);
	}

	public Route getLoginRoute() {
		throw new NotImplementedException();
	}
}
