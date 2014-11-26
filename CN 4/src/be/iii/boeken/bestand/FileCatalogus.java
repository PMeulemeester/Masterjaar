package be.iii.boeken.bestand;

import be.iii.boeken.Boek;
import be.iii.boeken.Catalogus;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.util.ArrayList;
import java.util.List;


public class FileCatalogus implements Catalogus {

    protected List<Boek> boeken = new ArrayList();

    private final String bestand;
    private final CatalogusSchrijver schrijver;
    private final CatalogusLezer lezer;

    public FileCatalogus(String bestand, CatalogusSchrijver schrijver,
            CatalogusLezer lezer) {
        this.bestand = bestand;
        this.schrijver = schrijver;
        this.lezer = lezer;
    }
    
    @Override
    public void leesBoeken() throws IOException {
        boeken = lezer.leesBoeken(bestand);
    }

    public void schrijfCatalogus(String bestand) throws IOException {
        schrijfCatalogus(new FileOutputStream(bestand));
    }

    public void schrijfCatalogus(OutputStream out) throws IOException {
        schrijver.schrijfCatalogus(boeken, out);
    }

    @Override
    public List<Boek> geefBoeken() {
        return boeken;
    }
    
    @Override
    public void voegBoekToe(Boek boek) {
        boeken.add(boek);
    }

    @Override
    public void sluitEnBewaar() throws IOException {
        schrijfCatalogus(bestand);
    }

}
