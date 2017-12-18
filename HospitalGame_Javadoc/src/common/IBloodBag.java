package common;

/**
 * Interface for bloodbag
 *
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public interface IBloodBag extends IBonusPointItem {

    /**
     * Returns the bloodtype of the bloodbag.
     * @return The bloodtype of the bloodbag.
     */
    BloodType getBloodType();
}
