/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boeken.data;

/**
 *
 * @author vongenae
 */
public interface IPersoon {

    Adres getAdres();

    int getId();

    String getNaam();

    String getRijksregisternummer();

    String getVoornaam();
    
}
