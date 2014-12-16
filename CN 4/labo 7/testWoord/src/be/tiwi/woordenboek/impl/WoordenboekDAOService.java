/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
 * @author pieterm
 */
public class WoordenboekDAOService implements WoordenboekDAO{
    
    private DictService service;
    private DictServiceSoap port;
    
    public WoordenboekDAOService(){
        this.service=new DictService();
        this.port=service.getDictServiceSoap();
    }

    @Override
    public List<Woordenboek> getWoordenboeken() {
        List<Woordenboek> w=new ArrayList<>();
        List<Dictionary> dictlist=port.dictionaryList().getDictionary();
        for(Dictionary d: dictlist){
            w.add(new Woordenboek(d.getId(), d.getName()));
        }
        List<Dictionary> ext=port.dictionaryListExtended().getDictionary();
        for(Dictionary d: ext){
            w.add(new Woordenboek(d.getId(), d.getName()));
        }
        return w;
    }

    @Override
    public List<String> zoekWoorden(String prefix, String woordenboekId) {
        List<String> w=new ArrayList<>();
        List<DictionaryWord> wlist=port.matchInDict(woordenboekId, prefix, "prefix").getDictionaryWord();
        for(DictionaryWord d:wlist){
            w.add(d.getWord());
        }
        return w;
    }

    @Override
    public List<String> getDefinities(String woord, String woordenboekId) {
        List<String> w=new ArrayList<>();
        List<Definition> wlist=port.defineInDict(woordenboekId, woord).getDefinitions().getDefinition();
        for(Definition d:wlist){
            w.add(d.getWordDefinition());
        }
        return w;
    }
    
}
