/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boeken.data;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author vongenae
 */


@Entity
@Table(name="AUTEURS")
public class Auteur extends Persoon implements IAuteur {
    private String bibliografie;
    private Set<Boek> boeken = new HashSet<>();

    public Auteur() {
    }

    public Auteur(String rijksregisternummer, String naam, String voornaam, Adres adres) {
        super(rijksregisternummer, naam, voornaam, adres);
    }

    @Override
    public String getBibliografie() {
        return bibliografie;
    }

    public void setBibliografie(String bibliografie) {
        this.bibliografie = bibliografie;
    }
    
    @ManyToMany
    @JoinTable(name="BOEKEN_PER_AUTEUR", joinColumns=@JoinColumn(name="auteur"),
            inverseJoinColumns=@JoinColumn(name="boek"))
    public Set<Boek> getBoeken() {
        return boeken;
    }

    private void setBoeken(Set<Boek> boeken) {
        this.boeken = boeken;
    }
    
    
    public boolean add(Boek boek) {
        if (boek != null && !boeken.contains(boek)) {
            return boeken.add(boek);
        } else {
            return false;
        }
    }
    

}
