/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.iii.boeken.xml;

import be.iii.boeken.Auteur;
import be.iii.boeken.Boek;
import be.iii.boeken.bestand.CatalogusSchrijver;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author tiwi
 */
public class XMLCatalogusSchrijver implements CatalogusSchrijver {

    private void schrijfBoekenNaarDocument(List<Boek> boeken, Document document) {
        Element knoop = document.createElement(CatalogusUtil.BOOKS);
        document.appendChild(knoop);
        for (Boek boek: boeken) {
            document.getDocumentElement().appendChild(maakBoekKnoop(boek, document));
        }

    }

    private Element maakBoekKnoop(Boek boek, Document document) {
        Element boekKnoop = document.createElement(CatalogusUtil.BOOK);

        Element isbnKnoop = document.createElement(CatalogusUtil.ISBN);
        isbnKnoop.appendChild(document.createTextNode(boek.getIsbn()));
        boekKnoop.appendChild(isbnKnoop);

        Element titleKnoop = document.createElement(CatalogusUtil.TITLE);
        titleKnoop.appendChild(document.createTextNode(boek.getTitle()));
        boekKnoop.appendChild(titleKnoop);

        List<Auteur> auteurs = boek.getAuthor();
        for (Auteur auteur : auteurs) {
            boekKnoop.appendChild(maakAuteurKnoop(auteur, document));
        }

        return boekKnoop;
    }

    private Element maakAuteurKnoop(Auteur auteur, Document document) {
        Element auteurKnoop = document.createElement(CatalogusUtil.AUTEUR);

        Element nameKnoop = document.createElement(CatalogusUtil.NAME);
        nameKnoop.appendChild(document.createTextNode(auteur.getName()));
        auteurKnoop.appendChild(nameKnoop);

        if (auteur.getInitial() != null) {
            Element initialKnoop = document
                    .createElement(CatalogusUtil.INITIAL);
            initialKnoop.appendChild(document.createTextNode(auteur
                    .getInitial()));
            auteurKnoop.appendChild(initialKnoop);
        }

        List<String> firstnames = auteur.getFirstname();
        for (String voornaam : firstnames) {
            Element firstnameKnoop = document
                    .createElement(CatalogusUtil.FIRSTNAME);
            firstnameKnoop.appendChild(document.createTextNode((voornaam)));
            auteurKnoop.appendChild(firstnameKnoop);
        }

        return auteurKnoop;
    }

    @Override
    public void schrijfCatalogus(List<Boek> boeken, OutputStream out) throws IOException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            schrijfBoekenNaarDocument(boeken, document);
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(out);
            transformer.transform(source, result);
        } catch (TransformerException | ParserConfigurationException e) {
            throw new IOException(e.getMessage());
        }
    }
}
