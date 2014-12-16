/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.tiwi.woordenboek.impl;

import be.tiwi.woordenboek.data.Woordenboek;
import be.tiwi.woordenboek.data.WoordenboekDAO;
import com.aonaware.services.webservices.Definition;
import com.aonaware.services.webservices.DictService;
import com.aonaware.services.webservices.DictServiceSoap;
import com.aonaware.services.webservices.Dictionary;
import com.aonaware.services.webservices.DictionaryWord;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vongenae
 */
public class WoordenboekDAOService implements WoordenboekDAO {

    private DictService service;
    private DictServiceSoap port;

    public WoordenboekDAOService() {
        service = new DictService();
        port = service.getDictServiceSoap();
    }

    @Override
    public List<Woordenboek> getWoordenboeken() {
        List<Woordenboek> woordenboeken = new ArrayList<>();

        List<Dictionary> dictList = port.dictionaryList().getDictionary();
        for (Dictionary dict : dictList) {
            woordenboeken.add(
                    new Woordenboek(dict.getId(), dict.getName()));
        }

        List<Dictionary> dictExtList = port.dictionaryListExtended().getDictionary();
        for (Dictionary dict : dictExtList) {
            woordenboeken.add(
                    new Woordenboek(dict.getId(), dict.getName()));
        }
        return woordenboeken;
    }

    @Override
    public List<String> zoekWoorden(String prefix, String woordenboekId) {
        List<String> woorden = new ArrayList<>();
        List<DictionaryWord> wordList = port.matchInDict(woordenboekId, prefix, "prefix").getDictionaryWord();
        for (DictionaryWord word : wordList) {
            woorden.add(word.getWord());
        }
        return woorden;
    }

    @Override
    public List<String> getDefinities(String woord, String woordenboekId) {        
        List<String> definities = new ArrayList<>();
        List<Definition> definitionList = port.defineInDict(woordenboekId, woord).getDefinitions().getDefinition();
        for (Definition definition : definitionList) {
            definities.add(definition.getWordDefinition());
        }
        return definities;
    }
}
