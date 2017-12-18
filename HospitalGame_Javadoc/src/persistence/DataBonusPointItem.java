package persistence;

import common.IBonusPointItem;
import java.io.Serializable;

/**
 * Class to store bonus point item
 * 
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public class DataBonusPointItem extends DataItem implements IBonusPointItem, Serializable {

    /**
     * How many bonus points the item gives
     */
    public int bonusPoints;

    /**
     * Constructor for a bonus point item from the interface IBonusPointItem
     * @param item the given bonus point item
     */
    public DataBonusPointItem(IBonusPointItem item) {
        super(item);
        bonusPoints = item.getBonusPoints();
    }

    /**
     * 
     * @return the amount of the bonus points.
     */
    @Override
    public int getBonusPoints() {
        return bonusPoints;
    }

}
