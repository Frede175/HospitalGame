package business.Item;

import common.IPowerUpItem;
import common.ItemName;

    /**
    * Class to handle all powerupitems in the game
    *
    * @author Frederik Schultz Rosenberg
    * @author Andreas Bøgh Mølgaard-Andersen
    * @author Lars Bjerregaard Jørgensen
    * @author Robert Francisti
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
    
    /** 
     * gets item buff, updates and gets time left of buff
     * 
     * @param item of the concerned item
     */
    public PowerUpItem(IPowerUpItem item) {
        super(item);
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
    /**
     * setter for setLastUpdate function
     * @param lastUpdate is the latest update from a powerup item
     */
    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
