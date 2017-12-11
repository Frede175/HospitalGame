package persistence;

import common.IHighScore;
import java.io.Serializable;
import java.util.HashMap;

/**
 * Class to store high score
 * 
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public class DataHighScore implements IHighScore, Serializable {

    /**
     * A map for all the scores
     */
    private HashMap<String, Integer> highScore;

    /**
     * Constructor for high scores from the interface IHighScore
     * @param highScore the given high scores
     */
    public DataHighScore(IHighScore highScore) {
        this.highScore = highScore.getHighScore();
    }
    
    /**
     * 
     * @return a map of all the high scores.
     */
    @Override
    public HashMap<String, Integer> getHighScore() {
        return highScore;
    }

}
