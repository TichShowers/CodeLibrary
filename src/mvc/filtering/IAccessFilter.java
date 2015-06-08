package mvc.filtering;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import mvc.responses.ActionResult;

/**
 * 
 * @author Colin Bundervoet
 *
 */
public interface IAccessFilter {
	void initialize(Connection connection);
	boolean hasAccess(HttpServletRequest request);
	ActionResult redirect();
}
