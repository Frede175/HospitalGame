package common;

import java.util.HashMap;

/**
 * HighScore interface
 *
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public interface IHighScore {

    /**
     * Gets the highscore.
     * @return The highscore.
     */
    HashMap<String, Integer> getHighScore();
}
