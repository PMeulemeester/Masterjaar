package be.iii.boeken.xml;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;


public class SAXCatalogusDefaultHandler extends CatalogusMaker {
    
    public SAXCatalogusDefaultHandler() {
        super();
    }
    
    public DefaultHandler getDefaultHandler() {
        return new DefaultHandler() {
            @Override
            public void startElement(String namespaceURI, String localName,
                    String qualifiedName, Attributes atts) throws SAXException {
                System.out.println(namespaceURI + ":" + localName + " "
                        + qualifiedName);
                naamLaatsteElement = qualifiedName;
                if (qualifiedName.equals(CatalogusUtil.BOOK))
                    startBoekElement();
                else if (qualifiedName.equals(CatalogusUtil.AUTEUR))
                    startAuteurElement();
            }
            
            @Override
            public void endElement(String namespaceURI, String localName,
                    String qualifiedName) throws SAXException {
                if (qualifiedName.equals(CatalogusUtil.BOOK))
                    endBoekElement();
                else if (qualifiedName.equals(CatalogusUtil.AUTEUR))
                    endAuteurElement();
            }
            
            @Override
            public void characters(char[] text, int start, int length)
            throws SAXException {
                if (naamLaatsteElement.equals(CatalogusUtil.ISBN))
                    setIsbnTekst(text, start, length);
                else if (naamLaatsteElement.equals(CatalogusUtil.TITLE))
                    setTitleTekst(text, start, length);
                else if (naamLaatsteElement.equals(CatalogusUtil.NAME))
                    setNameTekst(text, start, length);
                else if (naamLaatsteElement.equals(CatalogusUtil.INITIAL))
                    setInitialTekst(text, start, length);
                else if (naamLaatsteElement.equals(CatalogusUtil.FIRSTNAME))
                    setFirstnameTekst(text, start, length);
            }
        };
    }
}