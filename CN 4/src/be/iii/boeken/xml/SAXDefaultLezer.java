/*
 * Created on Apr 26, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package be.iii.boeken.xml;

import be.iii.boeken.Boek;
import be.iii.boeken.bestand.CatalogusLezer;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

/**
 * @author vongenae
 *
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class SAXDefaultLezer implements CatalogusLezer {
    private SAXCatalogusDefaultHandler handler;
    private SAXParser parser;

    public SAXDefaultLezer( SAXCatalogusDefaultHandler handler)
            throws IOException, SAXException, ParserConfigurationException {
        this(null, handler);
    }

    public SAXDefaultLezer(String schema,
            SAXCatalogusDefaultHandler handler) throws IOException, SAXException,
            ParserConfigurationException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(true);
        if (schema != null) {
            factory.setNamespaceAware(true);
        }
        parser = factory.newSAXParser();

        if (schema != null) {
            parser.setProperty(CatalogusUtil.JAXP_SCHEMA_LANGUAGE,
                    CatalogusUtil.W3C_XML_SCHEMA);
            parser.setProperty(CatalogusUtil.JAXP_SCHEMA_SOURCE,
                    new File(schema));
        }
        this.handler = handler;
    }

    @Override
    public List<Boek> leesBoeken(String bestand) throws IOException {
        try {
        parser.parse(bestand, handler.getDefaultHandler());
        return handler.geefBoekenLijst();
        } catch (SAXException | IOException ex) {
            throw (new IOException(ex));
        }
    }
}
