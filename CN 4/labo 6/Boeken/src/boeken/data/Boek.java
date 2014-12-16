package boeken.data;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;


@Entity
@Table(name="BOEKEN")
public class Boek  implements java.io.Serializable {


     private int id;
     private String isbn;
     private String titel;
     private Uitgeverij uitgeverij;
     private Set<Auteur> auteurs = new HashSet<>();

    public Boek() {
    }

	
    public Boek(int id) {
        this.id = id;
    }  
    
    public Boek(String isbn, String titel) {
       this.isbn = isbn;
       this.titel = titel;
    }
   
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public int getId() {
        return this.id;
    }
    
    private void setId(int id) {
        this.id = id;
    }
    public String getIsbn() {
        return this.isbn;
    }
    
    private void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public String getTitel() {
        return this.titel;
    }
    
    public void setTitel(String titel) {
        this.titel = titel;
    }
    
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="uitgeverij")
    public Uitgeverij getUitgeverij() {
        return this.uitgeverij;
    }
    
    public void setUitgeverij(Uitgeverij uitgeverij) {
        this.uitgeverij = uitgeverij;
    }
    
    @ManyToMany(mappedBy="boeken", fetch = FetchType.EAGER)
    public Set<Auteur> getAuteurs() {
        return auteurs;
    }

    public void setAuteurs(Set<Auteur> auteurs) {
        this.auteurs = auteurs;
        for (Auteur auteur : auteurs) {
            if (!auteur.getBoeken().contains(this)) {
                auteur.add(this);
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Boek other = (Boek) obj;
        if (!Objects.equals(this.isbn, other.isbn)) {
            return false;
        }
        if (!Objects.equals(this.titel, other.titel)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.isbn);
        hash = 37 * hash + Objects.hashCode(this.titel);
        return hash;
    }
    
    
}


