package be.iii.boeken.xml;

import org.xml.sax.SAXException;
import org.xml.sax.ContentHandler;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;

public class SAXCatalogusContentHandler extends CatalogusMaker 
        implements ContentHandler {
    
    public SAXCatalogusContentHandler() {
        super();
    }
    
    @Override
    public void setDocumentLocator(Locator locator) {
    }
    
    @Override
    public void startDocument() throws SAXException {
    }
    
    @Override
    public void endDocument() throws SAXException {
    }
    
    @Override
    public void startPrefixMapping(String prefix, String uri)
    throws SAXException {
    }
    
    @Override
    public void endPrefixMapping(String prefix) throws SAXException {
    }
    
    @Override
    public void startElement(String namespaceURI, String localName,
            String qualifiedName, Attributes atts) throws SAXException {
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
    
    @Override
    public void ignorableWhitespace(char[] text, int start, int length)
    throws SAXException {
    }
    
    @Override
    public void processingInstruction(String target, String data)
    throws SAXException {
    }
    
    @Override
    public void skippedEntity(String name) throws SAXException {
    }
}