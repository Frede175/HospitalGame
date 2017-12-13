package common;

/**
 * PowerUpItem interface
 *
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public interface IPowerUpItem extends IItem {

    /**
     * Gets the buff.
     * @return The buff.
     */
    double getBuff();

    /**
     * Gets the time left of the buff.
     * @return The time left of the buff.
     */
    long getTimeLeftOfBuff();
}
