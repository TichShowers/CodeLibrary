package mvc.controllers;

import java.util.Map;

import mvc.responses.ActionResult;

/**
 * 
 * @author Colin Bundervoet
 *
 */
public abstract class ResourceController extends MvcController {
	
	
	@Override
	public ActionResult handle(String action, Map<String, String> parameters) {
		int id = parseId(parameters.get("id"));
		
		switch(action){
			case "index" : return index();
			case "new" : return create();
			case "edit" : return edit(id);
			case "delete": return delete(id);
			case "show": return show(id);
			case "update": return save(id);
			case "store": return save();
		}
		return null;
	}
	
	public abstract ActionResult index();
	public abstract ActionResult show(int index);
	public abstract ActionResult create();
	public abstract ActionResult edit(int index);
	public abstract ActionResult delete(int index);
	public abstract ActionResult save(int index);
	public abstract ActionResult save();
	
	public int parseId(String id)
	{
		if(id == null)
		{
			return 0;
		}
		
		try{
			return Integer.parseInt(id);
		}
		catch(NumberFormatException e)
		{
			return 0;
		}
	}
}
