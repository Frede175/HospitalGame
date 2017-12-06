/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

/**
 *
 * @author Tobias
 */
public class Score {
    
    /**
     * name of the person
     */
    private final String NAME;
    
    /**
     * SCORE of the person
     */
    private final int SCORE;
    
    /**
     * Constructor of the person
     * @param name of the person
     * @param score of the person
     */
    public Score(String name, int score)
    {
        this.NAME = name;
        this.SCORE = score;
    }

    /**
     * Getter for the name of the person
     * @return the name
     */
    public String getName() {
        return NAME;
    }

    /**
     * Getter for the SCORE of the person
     * @return the SCORE
     */
    public int getScore() {
        return SCORE;
    }    
}
