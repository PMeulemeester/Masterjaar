package be.iii.boeken.xml;

import be.iii.boeken.Auteur;
import be.iii.boeken.Boek;
import be.iii.boeken.bestand.CatalogusLezer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import java.io.File;
import java.io.IOException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DOMLezer implements CatalogusLezer {
    private DocumentBuilder builder;

    public DOMLezer() throws ParserConfigurationException,
            SAXParseException, SAXException, IOException {
        this( null);
    }

    public DOMLezer(String schema)
            throws ParserConfigurationException, SAXParseException,
            SAXException, IOException {
        // factory
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // schema gebruiken?
        if (schema != null) {
            factory.setValidating(true);
            factory.setNamespaceAware(true);
            factory.setAttribute(CatalogusUtil.JAXP_SCHEMA_LANGUAGE,
                    CatalogusUtil.W3C_XML_SCHEMA);
            factory.setAttribute(CatalogusUtil.JAXP_SCHEMA_SOURCE, new File(
                    schema));
        }
        // builder
        builder = factory.newDocumentBuilder();
        builder.setErrorHandler(new FoutAfhandeling());
        
    }

    // DOM --> List
    @Override
    public List<Boek> leesBoeken(String bestand) throws IOException{
        // XML --> DOM
        Document document;
        try {
            document = builder.parse(new File(bestand));
        } catch (SAXException | IOException ex) {
            Logger.getLogger(DOMLezer.class.getName()).log(Level.SEVERE, null, ex);
            throw (new IOException(ex));
        }
        List<Boek> boeken = new ArrayList();
        NodeList boekKnopen = document.getElementsByTagName(CatalogusUtil.BOOK);
        for (int i = 0; i < boekKnopen.getLength(); i++) {
            Node boek = boekKnopen.item(i);
            boeken.add(maakBoekObject(boek));
        }
        return boeken;
    }

    private Boek maakBoekObject(Node boekKnoop) {
        NodeList kinderen = boekKnoop.getChildNodes();
        String isbn = null, title = null;
        ArrayList auteurs = new ArrayList();
        for (int i = 0; i < kinderen.getLength(); i++) {
            Node kind = kinderen.item(i);
            if (kind.getNodeName().equals(CatalogusUtil.ISBN)) {
                isbn = kind.getFirstChild().getNodeValue();
            } else if (kind.getNodeName().equals(CatalogusUtil.TITLE)) {
                title = kind.getFirstChild().getNodeValue();
            } else if (kind.getNodeName().equals(CatalogusUtil.AUTEUR)) {
                auteurs.add(maakAuteurObject(kind));
            }
        }
        return new Boek(isbn, title, auteurs);
    }

    private Auteur maakAuteurObject(Node auteurKnoop) {
        NodeList kinderen = auteurKnoop.getChildNodes();
        String name = null, initial = null;
        ArrayList firstname = new ArrayList();
        for (int i = 0; i < kinderen.getLength(); i++) {
            Node kind = kinderen.item(i);
            if (kind.getNodeName().equals(CatalogusUtil.NAME)) {
                name = kind.getFirstChild().getNodeValue();
            } else if (kind.getNodeName().equals(CatalogusUtil.INITIAL)) {
                initial = kind.getFirstChild().getNodeValue();
            } else if (kind.getNodeName().equals(CatalogusUtil.FIRSTNAME)) {
                firstname.add(kind.getFirstChild().getNodeValue());
            }
        }
        return new Auteur(name, initial, firstname);
    }

}
