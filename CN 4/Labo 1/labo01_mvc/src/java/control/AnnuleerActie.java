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
public class AnnuleerActie extends AbstractConcertActie{

    public AnnuleerActie() {
        titel = ReservatieActie.TITEL_RESERVATIE;
        view = ReservatieActie.JSP_RESERVATIE;
    }

    @Override
    protected void doActie(HttpServletRequest request, HttpServletResponse response) throws Exception {
    }
    
}
