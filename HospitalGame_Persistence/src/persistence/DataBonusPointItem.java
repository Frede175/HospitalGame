/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import common.IBonusPointItem;
import common.IItem;
import java.io.Serializable;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class DataBonusPointItem extends DataItem implements IBonusPointItem, Serializable {

    public int bonusPoints;

    public DataBonusPointItem(IBonusPointItem item) {
        super((IItem)item);
        bonusPoints = item.getBonusPoints();
    }

    
    
    
    @Override
    public int getBonusPoints() {
        return bonusPoints;
    }

}
