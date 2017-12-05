/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import common.IHighScore;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class BusinessHighScore implements IHighScore {
    
    /**
     * How many high scores is saved/max
     */
    private final int store = 10;
    
    /**
     * If the high scores has been updated
     */
    private boolean isDirty = false;
    
    private HashMap<String, Integer> scores;

    @Override
    public HashMap<String, Integer> getHighScore() {
        return scores;
    }

    public void load(IHighScore highScore) {
        scores = highScore.getHighScore();
    }

    public boolean isDirty() {
        return isDirty;
    }

    public boolean eligibleForHighscore(int score) {
        return Collections.min(scores.values()) < score;
    }
    
    public boolean isNameTaken(String name) {
        return scores.containsKey(name);
    }
    
    
    public boolean addHighScore(String name, int score) {
        if (eligibleForHighscore(score) && !isNameTaken(name)) {
            isDirty = true;
            if (scores.size() >= store) scores.remove(getMinKey());
            scores.put(name, score);
            return true;
        }
        return false;
    }
    
    private String getMinKey() {
        Entry<String, Integer> min = null;
        for (Entry<String, Integer> entry : scores.entrySet()) {
            if (min == null) {
                min = entry;
                break;
            }
            if (entry.getValue() < min.getValue()) min = entry;
        } 
        return min.getKey();
    }
}
