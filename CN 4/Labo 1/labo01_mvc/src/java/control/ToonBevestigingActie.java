/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Reservatie;

/**
 *
 * @author vongenae
 */
public class ToonBevestigingActie extends AbstractConcertActie {

    public static final String AANTALLEN_NUL = "aantallen nul";
    public static final String AANTAL_REDUCTIE_ONGELDIG = "aantalReductie ongeldig";
    protected static final String AANTAL_NORMAAL_ONGELDIG = "aantalNormaal ongeldig";
    protected static final String NAAM_ONGELDIG = "naam ongeldig";
    protected static final String PARAM_AANTAL_NORMAAL = "aantalNormaal";
    protected static final String PARAM_AANTAL_REDUCTIE = "aantalReductie";
    protected static final String PARAM_NAAM = "naam";
    protected static final String TITEL_BEVESTIG = "bevestig";
    protected static final String JSP_VRAAGBEVESTIGING = "vraagbevestiging.jsp";

    public ToonBevestigingActie() {
        init();
    }

    @Override
    protected void doActie(HttpServletRequest request, HttpServletResponse response) throws Exception {
        init(); 
        try {
            Reservatie reservatie = (Reservatie) request.getSession().getAttribute(ReservatieActie.ATT_RESERVATIE);

            String an = request.getParameter(PARAM_AANTAL_NORMAAL);
            int aantalNormaal = 0;
            if (!"".equals(an)) {
                aantalNormaal = Integer.parseInt(an);
            }
           if (aantalNormaal < 0) {
                throw new ReservatieException(AANTAL_NORMAAL_ONGELDIG);
           }
            reservatie.setAantalNormaal(aantalNormaal);

            String ar = request.getParameter(PARAM_AANTAL_REDUCTIE);
            int aantalReductie = 0;
            if (!"".equals(ar)) {
                aantalReductie = Integer.parseInt(ar);
            }
            if (aantalReductie < 0) {
                throw new ReservatieException(AANTAL_REDUCTIE_ONGELDIG);
            }

            reservatie.setAantalReductie(aantalReductie);

            if (aantalReductie == 0 && aantalNormaal == 0) {
                throw new ReservatieException(AANTALLEN_NUL);
            }

            String naam = request.getParameter(PARAM_NAAM); // Uiteraard beter met authenticatie (login...)
            if ("".equals(naam)) {
                throw new ReservatieException(NAAM_ONGELDIG);
            }

            reservatie.setGebruikersnaam(naam);
        } catch (ReservatieException e) {
            terugNaarReservatie(request, e);
        } catch (NumberFormatException e) {
            terugNaarReservatie(request, e);
        }
    }

    private void terugNaarReservatie(HttpServletRequest request, Exception e) {
        titel = ReservatieActie.TITEL_RESERVATIE;
        view = ReservatieActie.JSP_RESERVATIE;
        request.setAttribute(Controller.ATT_ERROR, e.getMessage());
    }
    
    private void init() {
        titel = TITEL_BEVESTIG;
        view = JSP_VRAAGBEVESTIGING;        
    }
}
