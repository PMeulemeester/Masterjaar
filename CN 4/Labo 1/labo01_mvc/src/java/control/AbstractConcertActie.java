/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author vongenae
 */
public abstract class AbstractConcertActie implements ConcertActie {
    
    protected String titel = "concerten";
    protected String view = "concerten.jsp";

    public AbstractConcertActie() {
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        doActie(request,response);
        vulViewDataIn(request);
    }
    
    protected void vulViewDataIn(HttpServletRequest request) {        
        request.setAttribute(Controller.TITEL, titel);
        request.setAttribute(Controller.VIEW, view);
    }
    protected abstract void doActie(HttpServletRequest request, HttpServletResponse response) throws Exception;
    
}
