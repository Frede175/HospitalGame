
package hospitalgame.item;

/**
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Tobias Ahrenschneider Sztuk
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */

public class BloodBag extends BonusPointItem {
    //private BloodType bloodType;

    /**
     * constructs and initialized the BloodBag object
     * @param bonusPoints the bonusPoints the item is to hold
     * @param name is the name of the item
     * @param weight is the weight of the item
     */
    public BloodBag(/*BloodType bloodType, */int bonusPoints, ItemName name, int weight) {
        super(bonusPoints, name, weight);
        
        //this.bloodType = bloodType;
        
    }
    
    
    
}
