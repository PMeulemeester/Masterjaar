/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.HashMap;
import java.util.Map;
import model.Concert;

/**
 *
 * @author Pieter
 */
public class DummyConcertDAO implements ConcertDAO {

    private HashMap<Long, Concert> concerten;

    public DummyConcertDAO() {
        concerten = new HashMap<>();
        concerten.put(1L, new Concert("Helmut goes classic", 20.0, 16.0));
        concerten.put(3L, new Concert("Justin Bieber goes classic", 80.0, 65.0));
        concerten.put(4L, new Concert("Eddy Wally goes classic", 180.0, 165.0));
    }

    @Override
    public Map<Long, Concert> getAlleConcerten() {
        return concerten;
    }

    @Override
    public Concert getConcert(long concertid) {
        return concerten.get(concertid);
    }
}
