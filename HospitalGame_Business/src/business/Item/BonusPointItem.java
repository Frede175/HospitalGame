package business.Item;

import common.IBonusPointItem;
import common.ItemName;

    /**
    * Class to handle all items that buff player
    *
    * @author Frederik Schultz Rosenberg
    * @author Andreas Bøgh Mølgaard-Andersen
    * @author Lars Bjerregaard Jørgensen
    * @author Robert Francisti
    */
public class BonusPointItem extends Item implements IBonusPointItem {

    /**
     * the amount of bonusPoints the gives
     */
    private int bonusPoints;

    /**
     * constructor for a bonusPointItem
     *
     * @param bonusPoints the bonusPoints of the item to be constructed
     * @param name the name of the item to be constructed
     * @param weight the weight of the item to be constructed
     */
    public BonusPointItem(int bonusPoints, ItemName name, int weight) {
        super(weight, name);
        this.bonusPoints = bonusPoints;
    }
    
    public BonusPointItem(IBonusPointItem item) {
        super(item);
        bonusPoints = item.getBonusPoints();
    }

    /**
     *
     * @return bonusPoints of the item
     */
    @Override
    public int getBonusPoints() {
        return bonusPoints;
    }

}
