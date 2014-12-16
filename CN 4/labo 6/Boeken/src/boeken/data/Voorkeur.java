/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boeken.data;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author vongenae
 */
/*<class name="boeken.data.Voorkeur" schema="III" table="VOORKEUREN">
        <id name="id" type="int" column="ID">
            <generator class="identity"/>
        </id>
        <property name="maxPrijs" column="MAX_PRIJS"/>
        <many-to-one name="klant" unique="true" not-null="true" class="boeken.data.Persoon" />
    </class>*/
@Entity
@Table(name="VOORKEUREN")
public class Voorkeur implements Serializable {
    private int id;
    private String genre;
    private int maxPrijs;
    private Klant klant;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="MAX_PRIJS")
    public int getMaxPrijs() {
        return maxPrijs;
    }

    public void setMaxPrijs(int maxPrijs) {
        this.maxPrijs = maxPrijs;
    }

    @OneToOne
    @JoinColumn(name="klant")
    public Klant getKlant() {
        return klant;
    }

    public void setKlant(Klant klant) {
        this.klant = klant;
    }
    
    
}
