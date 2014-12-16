package boeken.data;
// Generated 18-jun-2013 20:10:31 by Hibernate Tools 3.2.1.GA

import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "MENSEN")
@Inheritance(strategy = InheritanceType.JOINED)
public class Persoon implements java.io.Serializable, IPersoon {

    private int id;
    private String rijksregisternummer;
    private String naam;
    private String voornaam;
    private Adres adres;

    public Persoon() {
    }

    public Persoon(int id) {
        this.id = id;
    }

    public Persoon(String rijksregisternummer, String naam, String voornaam, Adres adres) {
        this.rijksregisternummer = rijksregisternummer;
        this.naam = naam;
        this.voornaam = voornaam;
        this.adres = adres;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public int getId() {
        return this.id;
    }

    private void setId(int id) {
        this.id = id;
    }

    @Override
    public String getRijksregisternummer() {
        return this.rijksregisternummer;
    }

    private void setRijksregisternummer(String rijksregisternummer) {
        this.rijksregisternummer = rijksregisternummer;
    }

    @Override
    public String getNaam() {
        return this.naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    @Override
    public String getVoornaam() {
        return this.voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    @OneToOne(cascade = CascadeType.PERSIST)
    @Override
    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Persoon other = (Persoon) obj;
        if (!Objects.equals(this.rijksregisternummer, other.rijksregisternummer)) {
            return false;
        }
        if (!Objects.equals(this.naam, other.naam)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.rijksregisternummer);
        hash = 19 * hash + Objects.hashCode(this.naam);
        return hash;
    }
}
