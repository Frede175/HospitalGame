/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Data;

import common.IBonusPointItem;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class DataBonusPointItem extends DataItem implements IBonusPointItem {

    public int bonusPoints;

    @Override
    public int getBonusPoints() {
        return bonusPoints; 
    }

}
