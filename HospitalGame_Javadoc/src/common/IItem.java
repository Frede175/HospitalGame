package common;

/**
 * Item interface
 *
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public interface IItem {

    /**
     * Gets the weight of the item.
     * @return The weight of the item.
     */
    int getWeight();

    /**
     * Gets the name of the item.
     * @return The name of the item.
     */
    ItemName getName();
}
