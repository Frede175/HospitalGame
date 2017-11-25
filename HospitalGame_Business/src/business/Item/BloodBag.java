/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Item;

import common.BloodType;
import common.IBloodBag;
import common.ItemName;

/**
 *
 * @author andreasmolgaard-andersen
 */

public class BloodBag extends BonusPointItem implements IBloodBag{

    private BloodType bloodType;

    public BloodBag(int bonusPoints, ItemName name, int weight,BloodType bloodType) {
        super(bonusPoints, name, weight);
        this.bloodType = bloodType;    
    }
    
    /**
     * compares the bloodType of two BloodBags
     * @param pO is the bloodbag being compared
     * @return true if bloodType is the same
     */
    public boolean equals(BloodBag pO){
        if (pO.getBloodType() == bloodType) {
            return true;
        } else {
            return false;
        }          
    }
    
    /**
     * 
     * @return the bloodType of the bloodBag
     */
    @Override
    public BloodType getBloodType() {
        return bloodType;
    }


    
}

