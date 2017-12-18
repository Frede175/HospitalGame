/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import common.IHighScore;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

/**
 * UIHighscore
 * 
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public class UIHighScore {

    /**
     * List of the scores
     */
    private final List<Score> SCORELIST;
    
    /**
     * Constructor for this class
     * @param highScore takes in a hashmap
     */
    public UIHighScore(IHighScore highScore){
        SCORELIST = new ArrayList<>();
        if(highScore != null){
            for(Entry<String, Integer> entry : highScore.getHighScore().entrySet()){
                SCORELIST.add(new Score(entry.getKey(), entry.getValue()));
            }
        }
    }

    /**
     * Getter for the score list
     * @return the arraylist that has been given in the constructor 
     */
    public List<Score> getScoreList() {
        return SCORELIST;
    }
    
    
}
