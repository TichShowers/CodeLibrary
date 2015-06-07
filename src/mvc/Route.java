package mvc;

import java.util.ArrayList;
import java.util.List;

import mvc.filtering.IAccessFilter;

public class Route {

	private String controller;
	private String action;
	private List<IAccessFilter> filters;

	public String getController() {
		return controller;
	}

	public void setController(String controller) {
		this.controller = controller;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public List<IAccessFilter> getFilters() {
		return filters;
	}
	
	public void addFilter(IAccessFilter filter){
		filters.add(filter);
	}

	public Route(String controller, String action) {
		this(controller, action, new ArrayList<IAccessFilter>());
	}

	public Route(String controller, String action, List<IAccessFilter> filters) {
		this.controller = controller;
		this.action = action;
		this.filters = filters;
	}

}
