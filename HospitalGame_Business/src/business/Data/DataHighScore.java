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

    
    public DataHighScore(IHighScore highScore) {
        this.highScore = highScore.getHighScore();
    }
    
    
    @Override
    public HashMap<String, Integer> getHighScore() {
        return highScore;
    }

}
