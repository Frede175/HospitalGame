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
    /**
     * adds HashMap to scores
     * loads all highscores
     * @param highScore the highscore
     */

    public void load(IHighScore highScore) {
        scores = new HashMap<>();
        if (highScore == null) return;
        scores.putAll(highScore.getHighScore());
    }

    public boolean isDirty() {
        return isDirty;
    }
    /**
     * checks if the score is eligible for a highscore
     * @param score the score that represents a HighScore
     * @return a place on the highscore
     */
    public boolean eligibleForHighscore(int score) {
        if(scores.isEmpty() || scores.size() < store) return true;
        return Collections.min(scores.values()) < score;
    }
    /**
     * checks if the name can be used
     * @param name of the player
     * @return name in highscore
     */
    public boolean isNameTaken(String name) {
        return scores.containsKey(name);
    }
    /**
     * adds highscore if score and name is eligible and not taken
     * @param name of the player
     * @param score of the player
     * @return adds a highscore
     */
    
    
    public boolean addHighScore(String name, int score) {
        if (eligibleForHighscore(score) && !isNameTaken(name)) {
            isDirty = true;
            if (scores.size() >= store) scores.remove(getMinKey());
            scores.put(name, score);
            return true;
        }
        return false;
    }
    /**
     * 
     * @return 
     */
    
    private String getMinKey() {
        Entry<String, Integer> min = null;
        for (Entry<String, Integer> entry : scores.entrySet()) {
            if (min == null) {
                min = entry;
                
            } else if (entry.getValue() < min.getValue()) min = entry;
        } 
        return min.getKey();
    }
}
