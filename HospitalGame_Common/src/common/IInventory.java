package common;

import java.util.List;

/**
 * Inventory interface
 *
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public interface IInventory {

    /**
     * Gets an item of the given index.
     * @param index Which item to get.
     * @return The item of the given index.
     */
    IItem getItem(int index);

    /**
     * Gets all the items in the inventory.
     * @return All the items in the inventory.
     */
    List<? extends IItem> getItems();

    /**
     * Gets an item by its name.
     * @param name Which item to get.
     * @return The item of the given name.
     */
    IItem[] getItemsByName(ItemName name);

    /**
     * Gets the inventorys ID.
     * @return The inventorys ID.
     */
    int getInventoryID();

    /**
     * Gets the max weight for the inventory.
     * @return The max weight for the inventory.
     */
    int getMaxWeight();
}
