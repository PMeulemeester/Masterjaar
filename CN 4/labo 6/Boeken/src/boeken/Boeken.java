/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boeken;

import boeken.data.Adres;
import boeken.data.Auteur;
import boeken.data.Persoon;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author vongenae
 */
public class Boeken {

    Random random;

    private Boeken() {
        random = new Random();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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

    private String[] namenUitgeverijen() {
        String[] namen = {"De eenhoorn", "De bezige bij", "Lannoo", "O'reilly"};
        return namen;
    }


    private String maakTitel() {
        int aantal = 1 + random.nextInt(4);
        StringBuilder tekst = new StringBuilder("titel");
        for (int i = 0; i < aantal; i++) {
            tekst.append(" woord");
        }
        return tekst.toString();
    }
}
