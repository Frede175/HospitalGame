/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Data;

import common.IHighScore;
import java.util.HashMap;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class DataHighScore implements IHighScore {

    private HashMap<String, Integer> highScore;

    @Override
    public HashMap<String, Integer> getHighScore() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    boolean save(IHighScore highscore) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.  
    }

    boolean load() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

}
