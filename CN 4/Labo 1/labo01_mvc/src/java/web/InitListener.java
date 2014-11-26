/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.ConcertDAO;
import dao.DummyConcertDAO;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author Pieter Simoens
 */
@WebListener
public class InitListener implements ServletContextListener {

    public static final String CONCERTDAO = "concertDao";
    private static final String PARAM_CONCERTDAO = "daoKlasse";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        ConcertDAO concertData = null;
        try {
            Constructor ctor = Class.forName(sce.getServletContext().getInitParameter(PARAM_CONCERTDAO)).getDeclaredConstructor();
            concertData = (ConcertDAO) ctor.newInstance();
            sc.setAttribute(CONCERTDAO, concertData);
            
        } catch (Exception ex) { 
            //production code should have better error handling
            Logger.getLogger(InitListener.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }        
    }

    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Context destroyed");
    }
}
