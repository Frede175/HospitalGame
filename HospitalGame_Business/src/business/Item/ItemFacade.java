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
import java.util.Objects;

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
     * hold the time of the lastUpdatee
     */
    private long lastUpdate;
    

    /**
     * Creates an inventory
     * @param maxWeight the maxWeight of the item being created
     * @return the ID of the inventory created
     */
    @Override
    public int createInventory(int maxWeight){
        Inventory inventory = new Inventory(maxWeight);
        inventoryList.add(inventory);
        return inventory.getInventoryID();
    }
    
    /**
     * Adds an item to inventory with gives ID
     * @param inventoryID ID of the inventory to add item to
     * @param item the item being added
     * @return true if the item has been added, else false
     */
    @Override
    public boolean addItem(int inventoryID, IItem item){
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
     * @param inventoryID is the ID of the inventory
     * @param item is the item being removed
     * @return true if the item has been removed, false if not
     */
    @Override
    public boolean removeItem(int inventoryID, IItem item){
        for (Inventory inventory : inventoryList) {
            if (inventory.getInventoryID() == inventoryID) {
                inventory.removeItem(item);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Activates a powerUpItem
     * @param item the item being activated
     * @return true if the item has been activated
     */
    @Override
    public boolean activateItem(IItem item){
        return false;
    }
            
    /**
     * updates the timeLeftOfBuff of a powerUpItem
     * @param powerUpItem is the item being updated
     * @param inventory
     */
    
    public void update(IPowerUpItem powerUpItem) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * 
     * @param inventoryID is the ID to find inventory with
     * @return an IInventory with given ID
     */
    @Override
    public IInventory getInventory(int inventoryID) {
        for (int i = 0; i < inventoryList.size(); i++) {
            if (inventoryList.get(i).getInventoryID() == inventoryID) {
                return inventoryList.get(i);
            }
        }
        return null;
    }
    
    @Override
    public void load(Objects[] objects){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public IInventory[] getInventories() {
        IInventory[] inventories = new IInventory[inventoryList.size()];        
            inventoryList.toArray(inventories);
            return inventories;
    }
}
