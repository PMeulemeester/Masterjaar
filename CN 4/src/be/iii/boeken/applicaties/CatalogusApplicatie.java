package be.iii.boeken.applicaties;

import be.iii.boeken.Auteur;
import be.iii.boeken.Boek;
import be.iii.boeken.Catalogus;
import be.iii.boeken.bestand.FileCatalogus;
import be.iii.boeken.xml.DOMLezer;
import be.iii.boeken.xml.SAXCatalogusContentHandler;
import be.iii.boeken.xml.SAXCatalogusDefaultHandler;
import be.iii.boeken.xml.SAXContentLezer;
import be.iii.boeken.xml.SAXDefaultLezer;
import be.iii.boeken.xml.XMLCatalogusSchrijver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class CatalogusApplicatie {

    private static void afdrukken(Catalogus catalogus) {
        System.out.println("Overzicht boeken");
        for (Boek boek : catalogus.geefBoeken()) {
            System.out.println("isbn " + boek.getIsbn());
            System.out.println("title " + boek.getTitle());
            for (Auteur auteur : boek.getAuthor()) {
                System.out.println("name " + auteur.getName());
                System.out.println("initial " + auteur.getInitial());
                System.out.println("firstname " + auteur.getFirstname());
            }
        }
        System.out.println();

    }

    private static Boek maakBoek() {
        List<Auteur> auteurs = new ArrayList<>();
        List<String> voornamen = new ArrayList<>();
        voornamen.add("Koen");
        voornamen.add("Jan");
        auteurs.add(new Auteur("De Ridder", "J.", voornamen));
        voornamen = new ArrayList<>();
        voornamen.add("Els");
        auteurs.add(new Auteur("Janssens", voornamen));
        return new Boek("000-222", "Mijn Titel", auteurs);
    }

    private static void doeIets(Catalogus catalogus) {
        afdrukken(catalogus);
        catalogus.voegBoekToe(maakBoek());
        afdrukken(catalogus);
    }

    private static void testCatalogus(Catalogus catalogus) {
        try {
            catalogus.leesBoeken();
            doeIets(catalogus);
            ((FileCatalogus) catalogus).schrijfCatalogus(System.out);
            ((FileCatalogus) catalogus).schrijfCatalogus("boeken3.xml");
            catalogus.sluitEnBewaar();
        } catch (IOException e) {
            System.out.println("Fout bij opstarten:" + e.getMessage());
        }
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, SAXParseException, IOException {
        Catalogus catalogus = new FileCatalogus("boekenxsd.xml",
                new XMLCatalogusSchrijver(), new DOMLezer());
        testCatalogus(catalogus);
        catalogus = new FileCatalogus("boeken.xml",
                new XMLCatalogusSchrijver(),
                new SAXContentLezer(new SAXCatalogusContentHandler()));
        testCatalogus(catalogus);
        catalogus = new FileCatalogus("boeken.xml",
                new XMLCatalogusSchrijver(),
                new SAXDefaultLezer(new SAXCatalogusDefaultHandler()));
        testCatalogus(catalogus);
        catalogus = new FileCatalogus("boekenxsd.xml",
                new XMLCatalogusSchrijver(),
                new SAXDefaultLezer("boeken.xsd", new SAXCatalogusDefaultHandler()));
        testCatalogus(catalogus);        
    }
}
