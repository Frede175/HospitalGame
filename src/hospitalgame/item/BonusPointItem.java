
package hospitalgame.item;

/**
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Tobias Ahrenschneider Sztuk
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */

public class BonusPointItem extends Item{
    private int bonusPoints;
    
    public BonusPointItem(int bonusPoints, ItemName name, int weight) {
        super(name, weight);
        this.bonusPoints = bonusPoints;
    }
    
    public int getBonusPoints() {
        return bonusPoints;
    }
    
}
