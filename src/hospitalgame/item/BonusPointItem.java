package hospitalgame.item;

/**
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Tobias Ahrenschneider Sztuk
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public class BonusPointItem extends Item {

    /**
     * private variable bonusPoints contains the amount of bonus points of the
     * item.
     */
    private int bonusPoints;

    /**
     * Constructs and initializes the BonusPointItem
     *
     * @param bonusPoints the amount of bonus points of the item
     * @param name the name of the item
     * @param weight the weight of the item
     */
    public BonusPointItem(int bonusPoints, ItemName name, int weight) {
        super(name, weight);
        this.bonusPoints = bonusPoints;
    }

    /**
     * returns the amount of bonus points
     *
     * @return int
     */
    public int getBonusPoints() {
        return bonusPoints;
    }
    
    @Override
    public String toString() {
        return getName().toString();
    }

}
