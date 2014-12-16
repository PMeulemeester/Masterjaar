/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boeken.data;

import javax.persistence.*;

/**
 *
 * @author vongenae
 */
@Entity
@Table(name = "KOPERS")
public class Klant extends Persoon {

    private Voorkeur voorkeuren;
    private String type;

    public Klant(String type, String rijksregisternummer, String naam, String voornaam, Adres adres) {
        super(rijksregisternummer, naam, voornaam, adres);
        this.type = type;
    }

    public Klant(String type) {
        this.type = type;
    }

    public Klant() {
        super();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @OneToOne(mappedBy="klant")
    public Voorkeur getVoorkeuren() {
        return voorkeuren;
    }

    public void setVoorkeuren(Voorkeur voorkeuren) {
        this.voorkeuren = voorkeuren;
    }
}
