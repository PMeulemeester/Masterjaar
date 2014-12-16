package boeken.data;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
@Entity
@Table(name="UITGEVERIJEN")
public class Uitgeverij implements java.io.Serializable, IUitgeverij {

    private int id;
    private String naam;
    private Adres adres;
    private Set<Boek> boeken = new HashSet();

    public Uitgeverij() {
    }

    public Uitgeverij(int id) {
        this.id = id;
    }

    public Uitgeverij(String naam, Adres adres) {
        this.naam = naam;
        this.adres = adres;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Override
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getNaam() {
        return this.naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    @OneToOne
    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }
    
    @OneToMany(mappedBy="uitgeverij")
    public Set<Boek> getBoeken() {
        return boeken;
    }

    private void setBoeken(Set<Boek> boeken) {
        this.boeken = boeken;
    }
    
    
    
    public boolean add(Boek boek) {        
        if (boek != null && !boeken.contains(boek)) {
            boolean result  = boeken.add(boek);
            if (!boek.getUitgeverij().equals(this)) {
                boek.setUitgeverij(this);
            }
            return result;
        } else {
            return false;
        }
    }
}
