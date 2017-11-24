/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Item;

import common.BloodType;
import common.IBloodBag;
import common.IBonusPointItem;
import common.IItem;
import common.ItemName;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class BloodBag extends BonusPointItem implements IBloodBag {

    public BloodBag(int bonusPoints, ItemName name, int weight) {
        super(bonusPoints, name, weight);
    }

    @Override
    public BloodType getBloodType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
