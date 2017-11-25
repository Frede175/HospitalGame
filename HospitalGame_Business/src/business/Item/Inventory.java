/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Item;

import common.IInventory;
import common.IItem;
import common.ItemName;
import java.util.ArrayList;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class Inventory implements IInventory {

    private int maxWeight;
    private int id;
    private ArrayList<IItem> items = new ArrayList<>();
    private static int nextID;

    /**
     * constructer for the Inventory
     * @param maxWeight the maxWeight of the inventory being constructed
     */
    public Inventory(int maxWeight) {
        this.maxWeight = maxWeight;
        id = nextID;
        nextID++;
    }

    /**
     * constructor for the inventory
     * @param inventory takes and IInventory and creates an Inventory
     */
    public Inventory(IInventory inventory) {
        // mangler getMaxWeight fra inventory this.maxWeight = inventory.getMaxWeight();
        // mangler getID fra inventory this.id = inventory.getID();
        // mangler getArrayListOfItems fra inventory this.items = inventory.getArrayListOfItems();
    }

    /**
     * adds an item to the inventory, only if it does not exceed the maxWeight
     * @param item is the item being added to inventory
     * @return true if the item has been added, false if not
     */
    public boolean addItem(IItem item) {
        if (item.getWeight() + getTotalWeight() <= maxWeight) {
            return items.add(item);
        } else {
            return false;
        }
    }

    /**
     * removes an item from the inventory
     * @param item is the item being removed
     * @return true if the item has been removed, false if not
     */
    public boolean removeItem(IItem item) {
        if (items.remove(item)) {
            return true;
        }
        return false;
    }

    /**
     * 
     * @param index is the index of the item being retrieved from items ArrayList
     * @return an IItem object
     */
    public IItem getItem(int index) {
        return items.get(index);
    }

    /**
     * returns IItems
     * @return the whole ArrayList items
     */
    public ArrayList<IItem> getItems() {
        return items;
    }

    /**
     * calculates weight of all items the in inventory
     * @return the weight of all items in the inventory
     */
    public int getTotalWeight() {
        int totalWeight = 0;
        for (IItem item : items) {
            totalWeight += item.getWeight();
        }
        return totalWeight;
    }

    /**
     * function to find items in the Inventory based on the name as parameter
     * @param name is the name to find in the ArrayList items
     * @return IItem[] Array
     */
    @Override
    public IItem[] getItemsByName(ItemName name) {

        ArrayList<Item> itemList = new ArrayList<>();

        for (IItem item : items) {
            if (name == item.getName()) {
                itemList.add((Item) item);
            }
        }
        Item[] IItem = new Item[itemList.size()];
        itemList.toArray(IItem);
        return IItem;
    }
    
    public int getInventoryID() {
        return id;
    }
}
