package mvc.controllers;

import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Colin Bundervoet
 *
 */
public class ControllerFactory
{
    private final IControllerDirectory directory;
    private Map<String, MvcController> controllers;

    public ControllerFactory(IControllerDirectory directory)
    {
        this.directory = directory;
        controllers = new TreeMap<>();
    }

    public MvcController giveController(String name)
    {
        if (controllers.containsKey(name))
        {
            return controllers.get(name);
        }
        else
        {
            try
            {
                controllers.put(name, directory.giveController(name));
            }
            catch (ClassNotFoundException ex)
            {
                Logger.getLogger(ControllerFactory.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
        return controllers.get(name);
    }
}