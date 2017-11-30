/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.common;

import common.IInventory;
import common.IItem;
import common.INPC;
import common.IPowerUpItem;
import java.util.Objects;

/**
 *
 * @author andreasmolgaard-andersen
 */
public interface IItemFacade {
    public int createInventory(int maxWeight);
    public boolean addItem(int inventoryID, IItem item);
    public boolean removeItem(int inventoryID, IItem item);
    public boolean activateItem(IItem item, int inventoryID, long startTime);
    public boolean update(IPowerUpItem powerUpItem, int inventoryID, long lastUpdate);
    public IInventory getInventory(int InventoryID);
    public void load(INPC[] objects);    
}

