/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gegevens;

import be.ugent.tiwi.immo.interfaces.IPand;
import be.ugent.tiwi.immo.interfaces.IType;
import be.ugent.tiwi.immo.interfaces.ImmoException;
import be.ugent.tiwi.immo.jdbc.implementatie.ImmoDataStorage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pieter
 */
public class Gegevens {
    private ImmoDataStorage st;
    private List<IType> types;

    public int getAantal() {
        return aantal;
    }

    public void setAantal(int aantal) {
        this.aantal = aantal;
    }
    private List<IPand> panden;
    private int aantal;
    
    public List<IPand> getPanden() {
        return panden;
    }

    public void setPanden(List<IPand> panden) {
        this.panden = panden;
    }


    public List<IType> getTypes() {
        return types;
    }

    public void setTypes(List<IType> types) {
        this.types = types;
    }

    public Gegevens() throws ClassNotFoundException, ImmoException {
        st=new ImmoDataStorage();
        System.out.println("aanmaak ok!");
        types=new ArrayList<IType>();
        st.setDriver("com.mysql.jdbc.Driver");
        st.setConnectieString("jdbc:mysql://localhost:3306/immo");
        st.setUsername("root");
        st.setWachtwoord("");
        types=st.getGebouwTypes();
    }
    
    public void insertpanden() throws ImmoException{
        panden=new ArrayList<IPand>();
        panden.clear();
        panden=st.getPanden(GebruikersKeuze.gebouwtype, GebruikersKeuze.min, GebruikersKeuze.max);
        aantal=panden.size();
    }
    
    
    
}
