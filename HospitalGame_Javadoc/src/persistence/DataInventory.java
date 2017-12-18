package persistence;

import common.IBloodBag;
import common.IInventory;
import common.IItem;
import common.IPowerUpItem;
import common.ItemName;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Class to store inventory
 * 
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public class DataInventory implements IInventory, Serializable {
    
    /**
     * All the items in the inventory, since it is a data object the items does not change.
     */
    private DataItem[] items;
    
    /**
     * The max weight the inventory can hold
     */
    private Integer maxWeight;
    
    /**
     * The ID of the inventory
     */
    private int inventoryID;

    /**
     * Constructor for DataInventory from the interface IInventory.
     * This converts all the IItems in the inventory to data items.
     * @param inventory the given inventory
     */
    public DataInventory(IInventory inventory) {
        items = new DataItem[inventory.getItems().size()];
        for (int i = 0; i < inventory.getItems().size(); i++) {
            switch (inventory.getItems().get(i).getName()) {
                case BLOODBAG:
                    items[i] = new DataBloodBag((IBloodBag)inventory.getItems().get(i));
                    break;
                case BANDAGE:
                case MORPHINE:
                    items[i] = new DataPowerUpItem((IPowerUpItem)inventory.getItems().get(i));
                    break;
                case IDCARD:
                    items[i] = new DataItem(inventory.getItems().get(i));
                    break;
                default:
                    throw new AssertionError(inventory.getItems().get(i).getName().name());
                
            }
        }
        maxWeight = inventory.getMaxWeight();
        inventoryID = inventory.getInventoryID();
        
    }

    /**
     * This is not protected, will throw indexOutOfBunce.
     * @param index of the item
     * @return the item at the index
     */
    @Override
    public IItem getItem(int index) {
        return items[index];
    }

    /**
     *
     * @return gets the items in the inventory
     */
    @Override
    public List<? extends IItem> getItems() {
        return Arrays.asList(items);
    }

    /**
     * THIS FUNCTION IS NOT SUPPORTED SINCE IT IS A DATA OBJECT.
     * @param name
     * @return 
     */
    @Override
    public IItem[] getItemsByName(ItemName name) {
        throw new UnsupportedOperationException("Invalid operation for data object.");
    }

    /**
     * 
     * @return the inventory ID
     */
    @Override
    public int getInventoryID() {
        return inventoryID;
    }

    /**
     * 
     * @return the max weight
     */
    @Override
    public int getMaxWeight() {
        return maxWeight;
    }

}
