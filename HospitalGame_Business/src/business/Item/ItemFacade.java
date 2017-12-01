/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Item;

import business.common.IItemFacade;
import common.IInventory;
import common.IItem;
import common.IPowerUpItem;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class ItemFacade implements IItemFacade {

    /**
     * an arrayList of all the inventories existing
     */
    private ArrayList<Inventory> inventoryList = new ArrayList<>();

    /**
     * Creates an inventory
     *
     * @param maxWeight the maxWeight of the item being created
     * @return the ID of the inventory created
     */
    @Override
    public int createInventory(int maxWeight) {
        Inventory inventory = new Inventory(maxWeight);
        inventoryList.add(inventory);
        return inventory.getInventoryID();
    }

    /**
     * Adds an item to inventory with gives ID
     *
     * @param inventoryID ID of the inventory to add item to
     * @param item the item being added
     * @return true if the item has been added, else false
     */
    @Override
    public boolean addItem(int inventoryID, IItem item) {
        for (Inventory inventory : inventoryList) {
            if (inventory.getInventoryID() == inventoryID) {
                inventory.addItem(item);
                return true;
            }
        }
        return false;
    }

    /**
     * removes an item from inventory with given ID
     *
     * @param inventoryID is the ID of the inventory
     * @param item is the item being removed
     * @return true if the item has been removed, false if not
     */
    @Override
    public boolean removeItem(int inventoryID, IItem item) {
        for (Inventory inventory : inventoryList) {
            if (inventory.getInventoryID() == inventoryID) {
                inventory.removeItem(item);
                return true;
            }
        }
        return false;
    }

    /**
     * updates the timeLeftOfBuff of a powerUpItem
     *
     * @param powerUpItem is the item being updated
     * @param inventoryID
     * @param lastUpdate
     * @return 
     */
    @Override
    public boolean update(IPowerUpItem powerUpItem, int inventoryID, long lastUpdate) {
        Inventory inventory = inventoryList.get(inventoryID);
        Item item = inventory.getItem((IItem) powerUpItem);
        if (item != null) {
            ((PowerUpItem) item).update(lastUpdate);
            return true;
        }
        return false;
    }

    /**
     *
     * @param inventoryID is the ID to find inventory with
     * @return an IInventory with given ID
     */
    @Override
    public IInventory getInventory(int inventoryID) {
        if (inventoryList.get(inventoryID).getInventoryID() == inventoryID) {
            return inventoryList.get(inventoryID);
        }
        return null;
    }

    /**
     * load function for inventories
     * @param inventories is the array list of inventories to be loaded into the game
     */
    @Override
    public void load(IInventory[] inventories) {
        Collections.sort(inventoryList);
        for (IInventory inventorie : inventories) {
            inventoryList.add(new Inventory(inventorie));
        }
    }

    /**
     * getter method for inventories
     * @return an array with all inventories
     */
    @Override
    public IInventory[] getInventories() {
        IInventory[] inventories = new IInventory[inventoryList.size()];
        inventoryList.toArray(inventories);
        return inventories;
    }

    /**
     * activates item
     * @param item is the item to be activated
     * @param inventoryID is the inventory to get the item from
     * @param startTime is the time that the buff was started
     * @return true if the item has been activated, false if not
     */
    @Override
    public boolean activateItem(IItem item, int inventoryID, long startTime) {
        Inventory inventory = inventoryList.get(inventoryID);
        Item i = inventory.getItem(item);
        if (i != null) {
            ((PowerUpItem) i).startBuff(startTime);
            return true;
        }
        return false;
    }
}
