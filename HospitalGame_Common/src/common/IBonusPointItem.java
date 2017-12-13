package common;

/**
 * BonusPointItem interface.
 *
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public interface IBonusPointItem extends IItem {
    
    /**
     * Returns the amount of bonus points of the item.
     * @return 
     */
    int getBonusPoints();
}
