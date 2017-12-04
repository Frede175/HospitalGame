package business.Item;

import common.IBonusPointItem;
import common.ItemName;

/**
 *
 * @author andreasmolgaard-andersen
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

    /**
     *
     * @return bonusPoints of the item
     */
    @Override
    public int getBonusPoints() {
        return bonusPoints;
    }

}
