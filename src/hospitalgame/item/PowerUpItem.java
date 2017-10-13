
package hospitalgame.item;

/**
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Tobias Ahrenschneider Sztuk
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public class PowerUpItem extends Item{
    /**
     * contains a variable, double, how powerful the buff is of the PowerUpItem
     */
    private double buff;
    /**
     * contains the time left of buff in currentTimeMillis
     */
    private long timeLeftOfBuff;
    /**
     * contains the time of the last update in currentTimeMillis
     */
    private long lastUpdate;
    
    /**
     * constructs and initializes the PowerUpItem
     * @param buff double how powerful the PowerUpItem is
     * @param timeLeftOfBuff how long the buff is to be active
     * @param name the name of the PowerUpItem
     * @param weight the weight if the PowerUpItem
     */
    public PowerUpItem(double buff, long timeLeftOfBuff, ItemName name, int weight) {
        super(name,weight);
        this.buff = buff;
        this.timeLeftOfBuff = timeLeftOfBuff;

    }
    
    /**
     * returns the buff variable
     * @return double
     */
    public double getBuff() {
        return buff;
    }
    
    /**
     * returns how much time of left of the buff currentTimeMillis
     * @return long
     */
    public long getTimeLeftOfBuff() {
        return timeLeftOfBuff;
    }
    
    /**
     * updates the PowerUpItem
     * @param CurrentTime is the time of the update, makes it possible to see the last update
     */
    public void update(long CurrentTime) {
        
    }
    
    /**
     * starts the PowerUpItem's buff
     * @param startTime is the time when you started the buff
     */
    public void startBuff(long startTime) {
        
    }
    
    
    
}
