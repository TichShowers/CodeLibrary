package common;

import filters.AdminAuthorizationFilter;
import mvc.AbstractRouter;
import mvc.Route;

public class RouteConfig extends AbstractRouter {

	@Override
	public void configure() {
		AdminAuthorizationFilter authFilter = new AdminAuthorizationFilter();
		
		route("/", "home", "index");
		route("/contact/", "home", "contact");
		route("/social/{link}/{username}/", "home", "social");
		route("/home/", "home", "id");
		
		resource("fragment");
		resource("language");
		resource("user", authFilter);
		
		route("/user/demote/{id}", "user", "demote", authFilter);
		route("/user/promote/{id}", "user", "promote", authFilter);
		route("/user/password/{id}", "user", "password", authFilter);
		
		route("/login/", "auth", "login");
		route("/logout/", "auth", "logout", true);
	}

	@Override
	public Route getLoginRoute() {
		return new Route("auth", "login");
	}
}
