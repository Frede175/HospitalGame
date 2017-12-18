package persistence;

import common.IItem;
import common.ItemName;
import java.io.Serializable;

/**
 * Class to store item
 * 
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public class DataItem implements IItem, Serializable {

    /**
     * The weight of the item
     */
    private int weight;
    
    /**
     * The name of the item
     */
    private ItemName name;
    
    /**
     * Constructor for data item from the interface IItem
     * @param item to be converted
     */
    public DataItem(IItem item) {
        weight = item.getWeight();
        name = item.getName();        
    }

    /**
     * 
     * @return the weight of the item
     */
    @Override
    public int getWeight() {
        return weight;
    }

    /**
     * 
     * @return the name of the item
     */
    @Override
    public ItemName getName() {
        return name;
    }


}
