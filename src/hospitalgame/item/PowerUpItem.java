
package hospitalgame.item;

/**
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Tobias Ahrenschneider Sztuk
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public class PowerUpItem extends Item{
    private double buff;
    private long timeLeftOfBuff, lastUpdate;
    
    public PowerUpItem(double buff, long timeLeftOfBuff, ItemName name, int weight) {
        super(name,weight);
        this.buff = buff;
        this.timeLeftOfBuff = timeLeftOfBuff;

    }
    
    public double getBuff() {
        return buff;
    }
    
    public long getTimeLeftOfBuff() {
        return timeLeftOfBuff;
    }
    
    public void update(long CurrentTime) {
        
    }
    
    public void startBuff(long startTime) {
        
    }
    
    
    
}
