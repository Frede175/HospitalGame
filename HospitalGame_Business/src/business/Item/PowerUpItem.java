/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Item;

import common.IItem;
import common.IPowerUpItem;
import common.ItemName;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class PowerUpItem extends Item implements IPowerUpItem {

    /**
     * holds variable buff, how powerful the item should be
     */
    private double buff;

    /**
     * holds how much time is left of the buff
     */
    private long timeLeftOfBuff;

    /**
     * holds the time of the lastUpdate
     */
    private long lastUpdate;

    /**
     * is the constructor for powerUpItem
     *
     * @param buff
     * @param timeLeftOfBuff
     * @param name
     * @param weight
     */
    public PowerUpItem(double buff, long timeLeftOfBuff, ItemName name, int weight) {
        super(weight, name);
        this.buff = buff;
        this.timeLeftOfBuff = timeLeftOfBuff;
    }

    public PowerUpItem(IPowerUpItem item) {
        super(((IItem) item).getWeight(), ((IItem) item).getName());
        buff = item.getBuff();
        lastUpdate = System.currentTimeMillis();
        timeLeftOfBuff = item.getTimeLeftOfBuff();
    }

    /**
     * getter method for buff
     *
     * @return buff
     */
    @Override
    public double getBuff() {
        return buff;
    }

    /**
     * getter method for the timeLeftOfBuff
     *
     * @return timeLeftOfBuff
     */
    @Override
    public long getTimeLeftOfBuff() {
        return timeLeftOfBuff;
    }

    /**
     * updates the time of powerUpItem
     *
     * @param currentTime is the currentTime
     */
    public void update(long currentTime) {
        timeLeftOfBuff -= currentTime - lastUpdate;
        lastUpdate = currentTime;
    }

    /**
     * starts the PowerUpItem's buff
     *
     * @param startTime is the time when you started the buff
     */
    public void startBuff(long startTime) {
        lastUpdate = startTime;
    }
}
