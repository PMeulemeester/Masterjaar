/*
 * Created on Apr 26, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package be.iii.boeken.xml;

import be.iii.boeken.Auteur;
import be.iii.boeken.Boek;
import java.util.ArrayList;

/**
 * @author vongenae
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class CatalogusMaker {
    /* lijst met boeken, lijst met auteurs van het huidig boek, 
       lijst met voornamen van de huidige auteur
     */
    private final ArrayList boeken;
    private ArrayList auteurLijst, voornamenLijst;
    private Auteur auteur; // huidige auteur
    private Boek boek; // huidig boek
    /* naam van het laatste element waarvoor de parser een event opriep
     */
    protected String naamLaatsteElement;   
    
    public CatalogusMaker() {
        boeken = new ArrayList();
        naamLaatsteElement = null;
        auteurLijst = null;
        voornamenLijst = null;
        auteur = null;
        boek = null;
    }
    
    public ArrayList geefBoekenLijst() {
        return boeken;
    }
    
    /* Bij het openen van een book-element worden een "leeg" Boek-object 
     * en een "lege" lijst van auteurs voor dit boek aangemaakt
     */
    protected void startBoekElement() {
        boek = new Boek();
        auteurLijst = new ArrayList();
    }
    
    /* Bij het sluiten van een book-element wordt de lijst van auteurs aan het 
     * boek toegekend en wordt het boek toegevoegd aan de lijst van boeken
     */
    protected void endBoekElement() {
        boek.setAuthor(auteurLijst);
        boeken.add(boek);
    }
    
    /* Bij het openen van een author-element worden een "leeg" Auteur-object 
     * en een "lege" lijst van voornamen voor deze auteur aangemaakt
     */
    protected void startAuteurElement() {
        auteur = new Auteur();
        voornamenLijst = new ArrayList();
    }
    
    /* Bij het sluiten van een author-element wordt de lijst van voornamen aan de 
     * auteur toegekend en wordt de auteur toegevoegd aan de lijst van auteurs
     * voor het huidige boek
     */
    protected void endAuteurElement() {
        auteur.setFirstname(voornamenLijst);
        auteurLijst.add(auteur);
    }
    
    /* Stelt het isbnnummer voor het huidige boek in */ 
    protected void setIsbnTekst(char[] text, int start, int length) {
        boek.setIsbn(new String(text,start,length));
    }
    
    /* Stelt de titel voor het huidige boek in */ 
    protected void setTitleTekst(char[] text, int start, int length) {
        boek.setTitle(new String(text,start,length));
    }
    
    /* Stelt de naam voor de huidige auteur in */
    protected void setNameTekst(char[] text, int start, int length) {
        auteur.setName(new String(text,start,length));
    }
    
    /* Stelt de initiaal voor de huidige auteur in */
    protected void setInitialTekst(char[] text, int start, int length) {
        auteur.setInitial(new String(text,start,length));
    }
    
    /* Voegt een voornaam voor de huidige auteur toe aan de lijst van de voornamen
     * van de huidige auteur
     */
    protected void setFirstnameTekst(char[] text, int start, int length) {
        voornamenLijst.add(new String(text,start,length));
    }
    
}
