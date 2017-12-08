/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import common.IPowerUpItem;
import java.io.Serializable;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class DataPowerUpItem extends DataItem implements IPowerUpItem, Serializable {

    double buff;
    long timeLeftOfBuff;

    DataPowerUpItem(IPowerUpItem item) {
        super(item);
        buff = item.getBuff();
        timeLeftOfBuff = item.getTimeLeftOfBuff();
    }

    @Override
    public double getBuff() {
        return buff;
    }

    @Override
    public long getTimeLeftOfBuff() {
        return timeLeftOfBuff;
    }

}
