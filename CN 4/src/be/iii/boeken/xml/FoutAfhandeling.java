/*
 * FoutAfhandeling.java
 *
 * Created on 4 mei 2004, 16:32
 */

package be.iii.boeken.xml;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXParseException;
import org.xml.sax.SAXException;
/**
 *
 * @author  vongenae
 */
public class FoutAfhandeling extends DefaultHandler{
    
    /** Creates a new instance of FoutAfhandeling */
    public FoutAfhandeling() {
    }
    
    @Override
    public void warning(SAXParseException e) throws SAXException{
        System.out.println("waarschuwing: " + e.getMessage());
    }
    
    @Override
    public void error(SAXParseException e) throws SAXException{
        System.out.println("fout: " + e.getMessage());
    }
    
}
