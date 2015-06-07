package mvc.filtering;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import mvc.responses.ActionResult;

public interface IAccessFilter {
	void initialize(Connection connection);
	boolean hasAccess(HttpServletRequest request);
	ActionResult redirect();
}
