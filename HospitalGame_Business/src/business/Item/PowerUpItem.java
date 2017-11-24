/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Item;

import common.IPowerUpItem;
import common.ItemName;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class PowerUpItem extends Item implements IPowerUpItem {
    
    private double buff;
    private long timeLeftOfBuff;
    private long lastUpdate;

    public PowerUpItem(int weight, ItemName name) {
        super(weight, name);
    }
    

    @Override
    public double getBuff() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long getTimeLeftOfBuff() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void PowerUpItem(double buff, long timeLeftOfBuff, ItemName name, int weight) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void PowerUpItem(IPowerUpItem item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void update(long currentTime) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void startBuff(long startTime) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
