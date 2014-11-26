/*
 * Created on Apr 26, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package be.tiwi.bo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author vongenae
 *
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
@XmlRootElement(name = "books")
public class Catalogus {

    protected List<Boek> boeken = new ArrayList<>();

    Boek[] getBoeken() {
        return boeken.toArray(new Boek[0]);
    }

    void voegBoekToe(Boek boek) {
        boeken.add(boek);
    }

}
