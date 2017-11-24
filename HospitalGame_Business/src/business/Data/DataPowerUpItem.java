/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Data;

import common.IPowerUpItem;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class DataPowerUpItem implements IPowerUpItem {
    double buff;
    long timeLeftOfBuff;
    long lastUpdate;

    @Override
    public double getBuff() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long getTimeLeftOfBuff() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
