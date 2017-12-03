/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Data;

import business.Item.Item;
import common.IBloodBag;
import common.IInventory;
import common.IItem;
import common.IPowerUpItem;
import common.ItemName;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class DataInventory implements IInventory {

    private DataItem[] items;
    private Integer maxWeight;
    private int inventoryID;

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

    @Override
    public IItem getItem(int index) {
        return items[index];
    }

    /**
     *
     * @return
     */
    @Override
    public List<? extends IItem> getItems() {
        return Arrays.asList(items);
    }

    @Override
    public IItem[] getItemsByName(ItemName name) {
        throw new UnsupportedOperationException("Invalid operation for data object.");
    }

    @Override
    public int getInventoryID() {
        return inventoryID;
    }

    @Override
    public int getMaxWeight() {
        return maxWeight;
    }

}
