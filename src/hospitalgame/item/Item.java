
package hospitalgame.item;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Tobias Ahrenschneider Sztuk
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public class Item {

    /**
     * weight is the weight of the item
     * name is the name of the item
     */
    private int weight;
    private ItemName name;
    
    /**
     * constructs and initializes the Item
     * @param name is the name of the item to be constructed
     * @param weight is the weight of the item to be constructed
     */
    public Item(ItemName name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    /**
     * is a method to get the name of the item
     * @return returns enum name of the item
     */
    public ItemName getName() {
        throw new NotImplementedException();
    }

    /**
     * is a method to get the weight of the item
     * @return returns int the weight of the item
     */
    public int getWeight() {
        throw new NotImplementedException();
    }

}
