/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.common;

import common.IInventory;
import common.IItem;
import common.IPowerUpItem;

    /**
    * Class to handle all the interface for itemFacade
    *
    * @author Frederik Schultz Rosenberg
    * @author Andreas Bøgh Mølgaard-Andersen
    * @author Lars Bjerregaard Jørgensen
    * @author Robert Francisti
    */


    /**
    * constructor for IItemFacade
    *
    * @author rober
    */
public interface IItemFacade {

    /**
     * Creates an inventory
     *
     * @param maxWeight the maxWeight of the item being created
     * @return the ID of the inventory created
     */
    public int createInventory(int maxWeight);

    /**
     * Adds an item to inventory with gives ID
     *
     * @param inventoryID ID of the inventory to add item to
     * @param item the item being added
     * @return true if the item has been added, else false
     */
    public boolean addItem(int inventoryID, IItem item);
    
    /**
     * removes an item from inventory with given ID
     *
     * @param inventoryID is the ID of the inventory
     * @param item is the item being removed
     * @return true if the item has been removed, false if not
     */
    public boolean removeItem(int inventoryID, IItem item);

    
     /**
     * activates item
     *
     * @param item is the item to be activated
     * @param inventoryID is the inventory to get the item from
     * @param startTime is the time that the buff was started
     * @return true if the item has been activated, false if not
     */
    public boolean activateItem(IItem item, int inventoryID, long startTime);
    
    /**
     * updates the timeLeftOfBuff of a powerUpItem
     *
     * @param powerUpItem is the item being updated
     * @param inventoryID
     * @param lastUpdate
     * @return
     */
    public boolean update(IPowerUpItem powerUpItem, int inventoryID, long lastUpdate);
    
      /**
     *
     * @param inventoryID is the ID to find inventory with
     * @return an IInventory with given ID
     */
    public IInventory getInventory(int inventoryID);

    /**
     * load function for inventories
     *
     * @param inventories is the array list of inventories to be loaded into the
     * game
     */
    public void load(IInventory[] inventories);

    /**
     * getter method for inventories
     *
     * @return an array with all inventories
     */
    public IInventory[] getInventories();

    /**
     * resets the inventory
     */
    public void reset();
}
