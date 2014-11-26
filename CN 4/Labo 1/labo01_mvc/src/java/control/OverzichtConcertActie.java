
package control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author vongenae
 */
public class OverzichtConcertActie extends AbstractConcertActie {

    protected static final String JSP_CONCERTEN = "concerten.jsp";
    protected static final String TITEL_CONCERTEN = "concerten";

    public OverzichtConcertActie() {
        titel = TITEL_CONCERTEN;
        view = JSP_CONCERTEN;
    }   

    @Override
    protected void doActie(HttpServletRequest request, HttpServletResponse response) {
    }
    
}
