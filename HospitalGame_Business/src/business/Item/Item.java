package business.Item;

import common.IItem;
import common.ItemName;

    /**
    * Class to handle items
    *
    * @author Frederik Schultz Rosenberg
    * @author Andreas Bøgh Mølgaard-Andersen
    * @author Lars Bjerregaard Jørgensen
    * @author Robert Francisti
    */
public abstract class Item implements IItem {

    /**
     * is the weight of the item
     */
    private int weight;

    /**
     * is the name of the item
     */
    private ItemName name;

    /**
     * constructor for Item
     *
     * @param weight the weight of the item
     * @param name the name of the item
     */
    public Item(int weight, ItemName name) {
        this.name = name;
        this.weight = weight;
    }
    /**
     * gets name and weight
     * @param item, an item in the game
     */
    public Item(IItem item) {
        name = item.getName();
        weight = item.getWeight();
    }

    /**
     * getter for Item
     *
     * @return the weight of the item
     */
    @Override
    public int getWeight() {
        return weight;
    }

    /**
     * getter for name of the item
     *
     * @return name
     */
    @Override
    public ItemName getName() {
        return name;
    }

}
