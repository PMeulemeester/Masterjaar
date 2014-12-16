/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boeken.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author vongenae
 */
public class BoekenDAO {

    SessionFactory sessionFactory;

    public BoekenDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void voegPersoonToe(Persoon persoon) {
        Session sessie = null;
        try {
            sessie = sessionFactory.openSession();
            sessie.beginTransaction();
            sessie.save(persoon.getAdres());
            sessie.save(persoon);
            sessie.getTransaction().commit();
            System.out.println("gelukt");
        } catch (Exception e) {
            if (sessie != null) {
                sessie.getTransaction().rollback();
            }
            Logger logger = Logger.getLogger(this.getClass().getName());
            logger.severe(e.getMessage());
        } finally {
            if (sessie != null) {
                sessie.close();
            }
        }
    }

    public void voegPersonenToe(Set<Persoon> personen) {
        Session sessie = null;
        try {
            sessie = sessionFactory.openSession();
            sessie.beginTransaction();
            for (Persoon persoon : personen) {
                sessie.save(persoon.getAdres());
                sessie.save(persoon);
            }
            sessie.getTransaction().commit();
        } catch (Exception e) {
            if (sessie != null) {
                sessie.getTransaction().rollback();
            }
            Logger logger = Logger.getLogger(this.getClass().getName());
            logger.severe(e.getMessage());
        } finally {
            if (sessie != null) {
                sessie.close();
            }
        }

    }

    public void voegAuteursToe(Set<Auteur> auteurs) {
        Set<Persoon> personen = new HashSet<>();
        personen.addAll(auteurs);
        voegPersonenToe(personen);
    }

    public void voegUitgeverijToe(Uitgeverij uitgeverij) {
        Session sessie = null;
        try {
            sessie = sessionFactory.openSession();
            sessie.beginTransaction();
            sessie.save(uitgeverij.getAdres());
            sessie.save(uitgeverij);
            sessie.getTransaction().commit();
        } catch (Exception e) {
            if (sessie != null) {
                sessie.getTransaction().rollback();
            }
            Logger logger = Logger.getLogger(this.getClass().getName());
            logger.severe(e.getMessage());
        } finally {
            if (sessie != null) {
                sessie.close();
            }
        }
    }

    public List<IUitgeverij> getUitgeverijen() {
        Session sessie = null;
        List<IUitgeverij> uitgeverijen = new ArrayList<>();
        try {
            sessie = sessionFactory.openSession();
            sessie.beginTransaction();
            uitgeverijen = sessie.createQuery("from Uitgeverij").list();
            sessie.getTransaction().commit();
        } catch (Exception e) {
            Logger logger = Logger.getLogger(this.getClass().getName());
            logger.severe(e.getMessage());
        } finally {
            if (sessie != null) {
                sessie.close();
            }
        }
        return uitgeverijen;
    }

    public List<IAuteur> getAuteurs() {
        Session sessie = null;
        List<IAuteur> auteurs = new ArrayList<>();
        try {
            sessie = sessionFactory.openSession();
            sessie.beginTransaction();
            auteurs = sessie.createQuery("from Auteur").list();
            sessie.getTransaction().commit();
        } catch (Exception e) {
            Logger logger = Logger.getLogger(this.getClass().getName());
            logger.severe(e.getMessage());
        } finally {
            if (sessie != null) {
                sessie.close();
            }
        }
        return auteurs;
    }

    public void voegBoekToe(Boek boek, int auteurId) {
        Session sessie = null;
        try {
            sessie = sessionFactory.openSession();
            sessie.beginTransaction();
            sessie.load(Uitgeverij.class, boek.getUitgeverij().getId());
            Auteur auteur = (Auteur) sessie.load(Auteur.class, auteurId);
            sessie.save(boek);
            boek.getAuteurs().add(auteur);
            sessie.getTransaction().commit();
        } catch (Exception e) {
            if (sessie != null) {
                sessie.getTransaction().rollback();
            }
            Logger logger = Logger.getLogger(this.getClass().getName());
            logger.severe(e.getMessage());
        } finally {
            if (sessie != null) {
                sessie.close();
            }
        }
    }

    public void voegBoekToe(Boek boek) {
        Session sessie = null;
        try {
            sessie = sessionFactory.openSession();
            sessie.beginTransaction();
            sessie.load(Uitgeverij.class, boek.getUitgeverij().getId());
            for (Auteur auteur : boek.getAuteurs()) {
                sessie.save(auteur.getAdres());
                sessie.save(auteur);
            }
            sessie.save(boek);
            sessie.getTransaction().commit();
        } catch (Exception e) {
            if (sessie != null) {
                sessie.getTransaction().rollback();
            }
            Logger logger = Logger.getLogger(this.getClass().getName());
            logger.severe(e.getMessage());
        } finally {
            if (sessie != null) {
                sessie.close();
            }
        }
    }

    public List<Boek> getBoekenPerUitgeverij(String uitgeverij) {
        Session sessie = null;
        List<Boek> boeken = new ArrayList<>();
        try {
            sessie = sessionFactory.openSession();
            sessie.beginTransaction();
            Query opdracht = sessie.createQuery("from Boek boek where boek.uitgeverij.naam = ?");
            opdracht.setString(0, uitgeverij);
            boeken = opdracht.list();
            sessie.getTransaction().commit();
        } catch (Exception e) {
            Logger logger = Logger.getLogger(this.getClass().getName());
            logger.severe(e.getMessage());
        } finally {
            if (sessie != null) {
                sessie.close();
            }
        }
        return boeken;
    }
    
    public Set<Boek> getBoekenPerAuteur(String auteur) {
        Session sessie = null;
        List<Boek> boeken = new ArrayList<>();
        try {
            sessie = sessionFactory.openSession();
            sessie.beginTransaction();
            SQLQuery opdracht = sessie.createSQLQuery("select * from III.BOEKEN b, III.BOEKEN_PER_AUTEUR ba, III.MENSEN a where b.ID=ba.BOEK and ba.AUTEUR=a.ID and a.NAAM = ? ");
            opdracht.addEntity("boek", Boek.class);
            opdracht.setString(0, auteur);
            boeken = opdracht.list();
            sessie.getTransaction().commit();
        } catch (Exception e) {
            Logger logger = Logger.getLogger(this.getClass().getName());
            logger.severe(e.getMessage());
        } finally {
            if (sessie != null) {
                sessie.close();
            }
        }
        return new HashSet(boeken);

    }
}
