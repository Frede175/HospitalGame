/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Data;

import common.BloodType;
import common.IBloodBag;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class DataBloodBag extends DataBonusPointItem implements IBloodBag{
    
    private BloodType bloodType;

    @Override
    public BloodType getBloodType() {
       return bloodType;
    }
    
}
//public class DataBloodBag extend DataBonusItem implements IBloodBag {
    
