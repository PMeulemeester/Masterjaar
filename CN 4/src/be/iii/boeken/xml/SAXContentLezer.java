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
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.XMLReader;
import org.xml.sax.SAXException;

/**
 * @author vongenae
 *
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class SAXContentLezer implements CatalogusLezer {

    private SAXCatalogusContentHandler handler;
    private XMLReader reader;

    public SAXContentLezer(SAXCatalogusContentHandler handler)
            throws IOException, SAXException, ParserConfigurationException {
        this(null, handler);
    }

    public SAXContentLezer(String schema,
            SAXCatalogusContentHandler handler) throws IOException, SAXException,
            ParserConfigurationException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(true);
        if (schema != null) {
            factory.setNamespaceAware(true);
        }
        SAXParser parser = factory.newSAXParser();
        if (schema != null) {
            parser.setProperty(CatalogusUtil.JAXP_SCHEMA_LANGUAGE,
                    CatalogusUtil.W3C_XML_SCHEMA);
            parser.setProperty(CatalogusUtil.JAXP_SCHEMA_SOURCE, new File(schema));
        }
        reader = parser.getXMLReader();
        reader.setContentHandler(handler);
        reader.setErrorHandler(new FoutAfhandeling());
        this.handler = handler;
    }

    @Override
    public List<Boek> leesBoeken(String bestand) throws IOException {
        try {
            reader.parse(bestand);
            return handler.geefBoekenLijst();
        } catch (SAXException ex) {
            Logger.getLogger(SAXContentLezer.class.getName()).log(Level.SEVERE, null, ex);
            throw (new IOException(ex));
        }
    }
}
