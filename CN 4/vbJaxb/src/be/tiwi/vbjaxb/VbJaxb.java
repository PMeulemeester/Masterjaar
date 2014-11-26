/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.tiwi.vbjaxb;

import be.tiwi.jaxb.Author;
import be.tiwi.jaxb.Book;
import be.tiwi.jaxb.Books;
import be.tiwi.jaxb.ObjectFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author vongenae
 */
public class VbJaxb {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            JAXBContext jctx = JAXBContext.newInstance("be.tiwi.jaxb");
            Unmarshaller um = jctx.createUnmarshaller();
            Books boeken = (Books) um.unmarshal(new FileInputStream("boeken.xml"));
            Marshaller m = jctx.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(boeken, System.out);
            voegBoekenToe(boeken);
            m.marshal(boeken, System.out);
            m.marshal(boeken, new FileOutputStream("boekenOutput.xml"));
        } catch (JAXBException | FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    protected static void voegBoekenToe(Books boeken) {
        ObjectFactory factory = new ObjectFactory();
        Author auteur = factory.createAuthor();
        auteur.setName("Denert");
        auteur.setInitial("K.");
        auteur.getFirstname().add("Bert");
        Book boek = factory.createBook();
        boek.setIsbn("45968752");
        boek.setTitle("Een nieuwe titel");
        boek.getAuthor().add(auteur);
        boeken.getBook().add(boek);
    }
}
