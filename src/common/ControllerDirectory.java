package common;

import mvc.controllers.IControllerDirectory;
import mvc.controllers.MvcController;

public class ControllerDirectory implements IControllerDirectory {

	@Override
	public MvcController giveController(String name)
			throws ClassNotFoundException {
		String controllerName = "controllers." + name.substring(0, 1).toUpperCase() + name.substring(1) + "Controller";
		
		return makeController(controllerName);
	}
	
	private MvcController makeController(String controllerName) {
   	 
    	System.out.println(
          "Router.makeController: trying to create " + controllerName);

    	try {
 	       Class<?> aClass = Class.forName( controllerName );
 	       return (MvcController) aClass.newInstance();

    	} catch (Exception e) {
        	try {
      	       Class<?> aClass = Class.forName( "howest." + controllerName );
      	       return (MvcController) aClass.newInstance();

         	} catch (Exception ee) {
      	       System.out.println(e.toString());
      	       return null;
         	}
 	   
    	}

    }

}
