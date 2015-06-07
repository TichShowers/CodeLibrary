package mvc.controllers;


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