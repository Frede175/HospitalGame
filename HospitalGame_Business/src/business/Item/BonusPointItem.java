package business.Item;

import common.IBonusPointItem;
import common.IItem;
import common.ItemName;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class BonusPointItem extends Item implements IBonusPointItem {

    private int bonusPoints;
    
    public BonusPointItem(int bonusPoints, ItemName name, int weight) {
        super(weight, name);
        this.bonusPoints = bonusPoints;
    }
    
    
    @Override
    public int getBonusPoints() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
