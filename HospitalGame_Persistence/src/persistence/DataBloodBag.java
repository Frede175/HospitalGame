/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import common.BloodType;
import common.IBloodBag;
import common.IBonusPointItem;
import java.io.Serializable;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class DataBloodBag extends DataBonusPointItem implements IBloodBag, Serializable {

    private BloodType bloodType;

    public DataBloodBag(IBloodBag item) {
        super((IBonusPointItem)item);
        bloodType = item.getBloodType();
    }

    @Override
    public BloodType getBloodType() {
        return bloodType;
    }

}
//public class DataBloodBag extend DataBonusItem implements IBloodBag {

