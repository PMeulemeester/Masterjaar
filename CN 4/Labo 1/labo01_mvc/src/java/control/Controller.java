/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Pieter
 */
public class Controller extends HttpServlet {

    protected static final String JSP_TEMPLATE = "template.jsp";
    protected static final String TITEL = "titel";
    protected static final String VIEW = "view";
    protected static final String ACTIE_DEFAULT = "concerten.do";
    protected static final String ACTIE_NAAR_RESERVATIE = "reservatie.do";
    protected static final String ACTIE_NAAR_BEVESTIGING = "naarBevestiging.do";
    protected static final String ACTIE_BEVESTIG_RESERVATIE = "bevestigReservatie.do";
    protected static final String ACTIE_ANNULEER_RESERVATIE = "wijzigReservatie.do";
    protected static final String ACTIES = "acties";
    protected static final String TITEL_ERROR = "foutpagina";
    protected static final String JSP_ERROR = "error.jsp";
    protected static final String ATT_ERROR = "fout";

    @Override
    public void init() throws ServletException {
        Map<String, ConcertActie> acties = new HashMap<String, ConcertActie>();
        acties.put(ACTIE_DEFAULT, new OverzichtConcertActie());
        acties.put(ACTIE_NAAR_RESERVATIE, new ReservatieActie());
        acties.put(ACTIE_NAAR_BEVESTIGING, new ToonBevestigingActie());
        acties.put(ACTIE_BEVESTIG_RESERVATIE, new BevestigActie());
        acties.put(ACTIE_ANNULEER_RESERVATIE, new AnnuleerActie());
        getServletContext().setAttribute(ACTIES, acties);

    }

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Map<String, ConcertActie> acties = (Map<String, ConcertActie>) getServletContext().getAttribute(ACTIES);
        ConcertActie actie = null;
        String pad = request.getServletPath();
        if (pad != null) {
            pad = pad.substring(1); //remove the starting '/' 
            actie = acties.get(pad);
        }
        if (actie == null) {
            actie = acties.get(ACTIE_DEFAULT);
        }
        try {
            actie.execute(request, response);
        } catch (Exception e) {
            
            request.setAttribute(TITEL, TITEL_ERROR);
            request.setAttribute(VIEW, JSP_ERROR);
            request.setAttribute(ATT_ERROR, e.getMessage());
        }
        request.getRequestDispatcher(JSP_TEMPLATE).forward(request, response);        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
