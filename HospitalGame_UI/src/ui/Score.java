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
    private final String name;
    
    /**
     * score of the person
     */
    private final int score;
    
    /**
     * Constructor of the person
     * @param name of the person
     * @param score of the person
     */
    public Score(String name, int score)
    {
        this.name = name;
        this.score = score;
    }

    /**
     * Getter for the name of the person
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the score of the person
     * @return the score
     */
    public int getScore() {
        return score;
    }    
}
