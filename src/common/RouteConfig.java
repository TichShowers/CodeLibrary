package common;

import filters.AdminAuthorizationFilter;
import mvc.AbstractRouter;
import mvc.Route;

/**
 * 
 * @author Colin Bundervoet
 *
 */
public class RouteConfig extends AbstractRouter {

	@Override
	public void configure() {
		AdminAuthorizationFilter authFilter = new AdminAuthorizationFilter();
		
		route("/", "home", "index");
		
		resource("fragment");
		resource("language");
		resource("user", authFilter);
		
		route("/user/demote/{id}", "user", "demote", authFilter);
		route("/user/promote/{id}", "user", "promote", authFilter);
		route("/user/password/{id}", "user", "password", authFilter);
		
		route("/comment/new/{fragment}", "comment", "new", true);
		route("/comment/delete/{fragment}/{id}", "comment", "delete", true);
		
		route("/settings/", "setting", "index", true);
		route("/settings/password/", "setting", "password", true);
		route("/settings/avatar/", "setting", "avatar", true);
		
		route("/login/", "auth", "login");
		route("/logout/", "auth", "logout", true);
	}

	@Override
	public Route getLoginRoute() {
		return new Route("auth", "login");
	}
}
