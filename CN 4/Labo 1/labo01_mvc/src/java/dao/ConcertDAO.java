package dao;

import java.util.Map;
import model.Concert;

/**
 *
 * @author Pieter
 */
public interface ConcertDAO {

    public Map<Long, Concert> getAlleConcerten();	// key is concert-id	

    public Concert getConcert(long concertid);
}
