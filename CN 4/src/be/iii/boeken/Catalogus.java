/*
 * Created on Apr 26, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package be.iii.boeken;

import java.io.IOException;
import java.util.List;
/**
 * @author vongenae
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface Catalogus {
	List<Boek> geefBoeken();
        void leesBoeken() throws IOException;
	void voegBoekToe(Boek boek);
	void sluitEnBewaar() throws IOException;
}
