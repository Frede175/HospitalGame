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
     * Class to handle all highscore related functions
    *
    * @author Frederik Schultz Rosenberg
    * @author Andreas Bøgh Mølgaard-Andersen
    * @author Lars Bjerregaard Jørgensen
    * @author Robert Francisti
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
    
    //HashMap with scores
    private HashMap<String, Integer> scores;

    /**
     * gets highScore
     *
     * @return scores
     */
    @Override
    public HashMap<String, Integer> getHighScore() {
        return scores;
    }

    /**
     * adds HashMap to scores loads all highscores
     *
     * @param highScore the highscore
     */
    public void load(IHighScore highScore) {
        scores = new HashMap<>();
        if (highScore == null) {
            return;
        }
        scores.putAll(highScore.getHighScore());
    }

    /**
     * checks if highscore has been changed
     *
     * @return true if it has been changed
     */
    public boolean isDirty() {
        return isDirty;
    }

    /**
     * returns true if the score  high score is enough to get on the high score list
     *
     * @param score the score that represents a HighScore
     * @return true if the score is high score is enough to get on the high score list
     */
    public boolean eligibleForHighscore(int score) {
        if (scores.isEmpty() || scores.size() < store) {
            return true;
        }
        return Collections.min(scores.values()) < score;
    }

    /**
     * checks if the name can be used
     *
     * @param name of the player
     * @return  true if the given name is taken
     */
    public boolean isNameTaken(String name) {
        return scores.containsKey(name);
    }

    /**
     * adds highscore if score and name is eligible and not taken
     *
     * @param name of the player
     * @param score of the player
     * @return true if the player got added
     */
    public boolean addHighScore(String name, int score) {
        if (eligibleForHighscore(score) && !isNameTaken(name)) {
            isDirty = true;
            if (scores.size() >= store) {
                scores.remove(getMinKey());
            }
            scores.put(name, score);
            return true;
        }
        return false;
    }

    /**
     * Get the key for the score that has the lowest score
     *
     * @return the name/key with the lowest score
     */
    private String getMinKey() {
        Entry<String, Integer> min = null;
        for (Entry<String, Integer> entry : scores.entrySet()) {
            if (min == null) {
                min = entry;

            } else if (entry.getValue() < min.getValue()) {
                min = entry;
            }
        }
        return min.getKey();
    }
}
