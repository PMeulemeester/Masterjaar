/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boeken.data;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author vongenae
 */
@Entity
@Table(name="ADRES")
public class Adres implements Serializable {

    private String straat;
    private String huisnummer;
    private Integer postcode;
    private String gemeente;
    private int id;

    public Adres() {
    }
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return this.id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public Adres(String straat, String huisnummer, Integer postcode, String gemeente) {
        this.straat = straat;
        this.huisnummer = huisnummer;
        this.postcode = postcode;
        this.gemeente = gemeente;
    }

    public String getStraat() {
        return this.straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public String getHuisnummer() {
        return this.huisnummer;
    }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    public Integer getPostcode() {
        return this.postcode;
    }

    public void setPostcode(Integer postcode) {
        this.postcode = postcode;
    }

    public String getGemeente() {
        return this.gemeente;
    }

    public void setGemeente(String gemeente) {
        this.gemeente = gemeente;
    }

}
