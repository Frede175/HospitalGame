/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import common.IHighScore;
import java.util.HashMap;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class BusinessHighScore implements IHighScore {

    private HashMap<String, Integer> scores;

    public void BusinessHighScore(IHighScore BusinessHighScore) {
        this.scores = scores;
    }

    @Override
    public HashMap<String, Integer> getHighScore() {
        return scores;
    }

}
