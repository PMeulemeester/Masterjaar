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
public class BevestigActie extends AbstractConcertActie {
    
    protected static final String TITEL_BEVESTIGD = "bevestigd";
    protected static final String JSP_BEVESTIGD = "bevestigd.jsp";

    public BevestigActie() {
        titel = TITEL_BEVESTIGD;
        view = JSP_BEVESTIGD;
    }

    @Override
    protected void doActie(HttpServletRequest request, HttpServletResponse response) throws Exception {
       
    }
    
}
