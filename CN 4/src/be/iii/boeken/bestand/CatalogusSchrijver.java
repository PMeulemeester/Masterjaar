/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.iii.boeken.bestand;

import be.iii.boeken.Boek;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 *
 * @author tiwi
 */
public interface CatalogusSchrijver {

    void schrijfCatalogus(List<Boek> boeken, OutputStream out) throws IOException;
    
}
