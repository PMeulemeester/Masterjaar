/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boeken;

import boeken.data.Adres;
import boeken.data.Auteur;
import boeken.data.Boek;
import boeken.data.BoekenDAO;
import boeken.data.IAuteur;
import boeken.data.IUitgeverij;
import boeken.data.Persoon;
import boeken.data.Uitgeverij;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author vongenae
 */
public class BoekenTest {

    Random random;
    BoekenDAO boekenDAO;

    private BoekenTest() {
        random = new Random();
    }

    private void setBoekenDAO(BoekenDAO boekenDAO) {
        this.boekenDAO = boekenDAO;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        BoekenDAO boekenDAO = new BoekenDAO(sessionFactory);
        BoekenTest test = new BoekenTest();
        test.setBoekenDAO(boekenDAO);
        //test.voegPersonenToe();
        //test.voegAuteursToe();
        test.voegUitgeverijenToe();
        test.printUitgeverijen();
        //test.maakBoeken();
        //test.maakBoekenVoorAuteur();
        //test.printBoeken("De eenhoorn");
        //test.printBoekenVanAuteur("De Moor");
        sessionFactory.close();
    }

    private void voegPersonenToe() {
        int aantal = 1 + random.nextInt(5);
        for (int i = 0; i < aantal; i++) {
            boekenDAO.voegPersoonToe(maakPersoon());
        }
    }

    private void voegAuteursToe() {
        Set<Auteur> auteurs = maakAuteurs();
        boekenDAO.voegAuteursToe(auteurs);
    }

    private Adres maakAdres() {
        String[] straten = {"Sportstraat", "Dorpsplein", "Kerkstraat", "Schoolstraat"};
        String[] nummers = {"3", "347B", "32/78", "35"};
        int[] postcodes = {9000, 1000, 2000, 8500};
        String[] gemeentes = {"Gent", "Brussel", "Antwerpen", "Kortrijk"};
        int gemeente = random.nextInt(postcodes.length);
        return new Adres(straten[random.nextInt(straten.length)],
                nummers[random.nextInt(nummers.length)],
                postcodes[gemeente], gemeentes[gemeente]);
    }

    private Persoon maakPersoon() {
        String[] voornamen = {"Els", "Koen", "Betty", "Diane", "Emmy", "Raf"};
        String[] namen = {"De Smedt", "Janssens", "De Moor", "Driesen", "Leemans", "Dierick"};
        Persoon persoon = new Persoon(Integer.toString(random.nextInt()),
                namen[random.nextInt(namen.length)],
                voornamen[random.nextInt(voornamen.length)],
                maakAdres());
        return persoon;
    }

    public Set<Auteur> maakAuteurs() {
        Set<Auteur> auteurs = new HashSet<>();

        int aantal = 1 + random.nextInt(5);
        for (int i = 0; i < aantal; i++) {
            Persoon persoon = maakPersoon();
            Auteur auteur = new Auteur(persoon.getRijksregisternummer(),
                    persoon.getNaam(),
                    persoon.getVoornaam(),
                    persoon.getAdres());
            auteur.setBibliografie(maakBibliografie());
            auteurs.add(auteur);
        }
        return auteurs;
    }

    private String maakBibliografie() {
        int aantal = 1 + random.nextInt(10);
        StringBuilder tekst = new StringBuilder("bla");
        for (int i = 0; i < aantal; i++) {
            tekst.append("bla");
        }
        return tekst.toString();
    }

    private void voegUitgeverijenToe() {
        String[] namen = {"De eenhoorn", "De bezige bij", "Lannoo", "O'reilly"};
        for (String naam : namen) {
            Adres adres = maakAdres();
            boekenDAO.voegUitgeverijToe(new Uitgeverij(naam, adres));
        }
    }

    public void maakBoeken() {
        int aantal = 1 + random.nextInt(4);
        for (int i = 0; i < aantal; i++) {
            Boek boek = maakBoek();
            Set<Auteur> auteurs = maakAuteurs();
            boek.setAuteurs(auteurs);
            boekenDAO.voegBoekToe(boek);
        }
    }

    public void maakBoekenVoorAuteur() {
        List<IAuteur> auteurs = boekenDAO.getAuteurs();
        if (!auteurs.isEmpty()) {
            int aantal = 1 + random.nextInt(4);
            for (int i = 0; i < aantal; i++) {
                Boek boek = maakBoek();
                IAuteur auteur = auteurs.get(random.nextInt(auteurs.size()));
                boekenDAO.voegBoekToe(boek, auteur.getId() );
            }
        }
    }

    private String maakTitel() {
        int aantal = 1 + random.nextInt(4);
        StringBuilder tekst = new StringBuilder("titel");
        for (int i = 0; i < aantal; i++) {
            tekst.append(" woord");
        }
        return tekst.toString();
    }

    private Boek maakBoek() {
        List uitgeverijen = boekenDAO.getUitgeverijen();
        Uitgeverij uitgeverij = (Uitgeverij) uitgeverijen.get(random.nextInt(uitgeverijen.size()));
        Boek boek = new Boek(Integer.toString(random.nextInt()), maakTitel());
        boek.setUitgeverij(uitgeverij);
        return boek;
    }

    private void printUitgeverijen() {
        List uitgeverijen = boekenDAO.getUitgeverijen();
        for (Object obj : uitgeverijen) {
         IUitgeverij uitgeverij = (IUitgeverij) obj;
         System.out.println(uitgeverij.getNaam());
         }
    }
    
    private void printBoeken(String uitgeverij) {
        List<Boek> boeken = boekenDAO.getBoekenPerUitgeverij(uitgeverij);
        for (Boek boek : boeken) {
         System.out.print(boek.getId()+": \""+boek.getTitel()+"\",");
         for (IAuteur auteur: boek.getAuteurs()) {
             System.out.print(" " + auteur.getNaam());
         }
         System.out.println(", " + boek.getUitgeverij().getNaam());
         }
    }
    private void printBoekenVanAuteur(String naam) {
        Set<Boek> boeken = boekenDAO.getBoekenPerAuteur(naam);
        for (Boek boek : boeken) {
         System.out.print(boek.getId()+": \""+boek.getTitel()+"\",");
         for (IAuteur auteur: boek.getAuteurs()) {
             System.out.print(" " + auteur.getNaam());
         }
         System.out.println(", " + boek.getUitgeverij().getNaam());
         }
    }
}
