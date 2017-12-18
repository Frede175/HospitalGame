package persistence;

import common.IPowerUpItem;
import java.io.Serializable;

/**
 * Class to store power up item
 * 
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public class DataPowerUpItem extends DataItem implements IPowerUpItem, Serializable {

    /**
     * How much less the player losses every second
     */
    private double buff;
    
    /**
     * How much time is left of the buff
     */
    private long timeLeftOfBuff;

    /**
     * Constructor for data power up item from the interface IPowerUpItem
     * @param item the given item
     */
    public DataPowerUpItem(IPowerUpItem item) {
        super(item);
        buff = item.getBuff();
        timeLeftOfBuff = item.getTimeLeftOfBuff();
    }

    /**
     * 
     * @return the buff of the item
     */
    @Override
    public double getBuff() {
        return buff;
    }

    /**
     * 
     * @return how much time is left of the buff
     */
    @Override
    public long getTimeLeftOfBuff() {
        return timeLeftOfBuff;
    }

}
