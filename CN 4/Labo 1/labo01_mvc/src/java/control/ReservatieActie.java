/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.ConcertDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Concert;
import model.Reservatie;

/**
 *
 * @author vongenae
 */
public class ReservatieActie extends AbstractConcertActie {

    protected static final String JSP_RESERVATIE = "reservatie.jsp";
    protected static final String TITEL_RESERVATIE = "reservatie";
    protected static final String PARAM_CONCERT_ID = "concertid";
    protected static final String ATT_RESERVATIE = "reservatie";
    protected static final String ATT_CONCERT = "concert";

    public ReservatieActie() {
        titel = TITEL_RESERVATIE;
        view = JSP_RESERVATIE;
    }

    @Override
    protected void doActie(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String cids = request.getParameter(PARAM_CONCERT_ID);
        Long cid = Long.parseLong(cids);
        ConcertDAO dao = (ConcertDAO) request.getServletContext().getAttribute(web.InitListener.CONCERTDAO);
        //place the selected concert on the session scope
        Concert concert = dao.getConcert(cid);
        request.getSession().setAttribute(ATT_CONCERT, concert);
        Reservatie reservatie = new Reservatie(cid);
        request.getSession().setAttribute(ATT_RESERVATIE, reservatie);
    }
}
