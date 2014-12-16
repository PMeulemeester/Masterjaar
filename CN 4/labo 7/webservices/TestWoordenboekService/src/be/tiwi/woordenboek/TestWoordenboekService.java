package be.tiwi.woordenboek;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.aonaware.services.webservices.DictService;
import com.aonaware.services.webservices.DictServiceSoap;
import java.util.List;
import com.aonaware.services.webservices.Dictionary;
import com.aonaware.services.webservices.DictionaryWord;
import com.aonaware.services.webservices.Strategy;

/**
 *
 * @author Wijnand
 */
public class TestWoordenboekService {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            DictService service = new DictService();
            DictServiceSoap port = service.getDictServiceSoap();

            List<Dictionary> dictList = port.dictionaryList().getDictionary();
            System.out.println("Woordenboeken:");
            for (Dictionary dict : dictList) {
                System.out.println(dict.getId() + ": " + dict.getName());
            }

            List<Dictionary> dictExtList = port.dictionaryListExtended().getDictionary();
            System.out.println("Extended:");
            for (Dictionary dict : dictExtList) {
                System.out.println(dict.getName());
            }

            List<Strategy> stratList = port.strategyList().getStrategy();
            System.out.println("Strategieen:");
            for (Strategy strat : stratList) {
                System.out.println(strat.getId() + ": " + strat.getDescription());
            }

            String searchWord = "obfus";
            String strategy = "prefix";
            List<DictionaryWord> wordList = port.match(searchWord, strategy).getDictionaryWord();
            System.out.println("Match \"obfus*\":");
            for (DictionaryWord word : wordList) {
                System.out.println(word.getWord());
            }
        } catch (Exception ex) {
            System.out.println("Fout: " + ex.getMessage());
        }

    }
}
