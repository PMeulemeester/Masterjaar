/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.tiwi.woordenboek.impl;

import be.tiwi.woordenboek.data.ArrayOfDictionaryWord;
import be.tiwi.woordenboek.data.Definition;
import be.tiwi.woordenboek.data.DictService;
import be.tiwi.woordenboek.data.DictServiceSoap;
import be.tiwi.woordenboek.data.Dictionary;
import be.tiwi.woordenboek.data.DictionaryWord;
import be.tiwi.woordenboek.data.Woordenboek;
import be.tiwi.woordenboek.data.WoordenboekDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.ws.Dispatch;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Service;
import java.io.StringReader;

/**
 *
 * @author vongenae
 */
public class WoordenboekDAOService implements WoordenboekDAO {

    List<Woordenboek> woordenboeken;
    Random random = new Random();
    private DictService service;
    private DictServiceSoap soap;

    public WoordenboekDAOService() {
        service=new DictService();
        soap=service.getDictServiceSoap();
        this.woordenboeken = initWoordenboeken();
    }

    @Override
    public List<Woordenboek> getWoordenboeken() {
        return woordenboeken;   
    }

    private List<Woordenboek> initWoordenboeken() {
        List<Woordenboek> lijstWoordenboeken = new ArrayList<>();
        for(Dictionary d:soap.dictionaryList().getDictionary()){
            Woordenboek wb=new Woordenboek(d.getId(), d.getName());
            lijstWoordenboeken.add(wb);
        }
        return lijstWoordenboeken;
    }

    @Override
    public List<String> zoekWoorden(String prefix, String woordenboekId) {
        List<String> woorden=new ArrayList<String>();
        List<DictionaryWord> zoekw=soap.matchInDict(woordenboekId, prefix, "prefix").getDictionaryWord();
        for(DictionaryWord dw:zoekw){
            woorden.add(dw.getWord());
        }
        return woorden;
    }

    @Override
    public List<String> getDefinities(String woord, String woordenboekId) {
        List<String> defs=new ArrayList<String>();
        List<Definition> definities=soap.defineInDict(woordenboekId, woord).getDefinitions().getDefinition();
        for(Definition d:definities){
            defs.add(d.getWordDefinition());
        }
        return defs;
    }
}
