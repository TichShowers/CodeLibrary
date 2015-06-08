package mvc.controllers;

/**
 * 
 * @author Colin Bundervoet
 *
 */
public interface IControllerDirectory
{
    /**
     * 
     * @param controllerName
     * @return
     * @throws ClassNotFoundException for Controllers
     */
    MvcController giveController(String controllerName) 
            throws ClassNotFoundException;
}