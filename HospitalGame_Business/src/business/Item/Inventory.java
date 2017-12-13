/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Item;

import common.IBloodBag;
import common.IInventory;
import common.IItem;
import common.IPowerUpItem;
import common.ItemName;
import java.util.ArrayList;

    /**
    * Class to handle inventory
    *
    * @author Frederik Schultz Rosenberg
    * @author Andreas Bøgh Mølgaard-Andersen
    * @author Lars Bjerregaard Jørgensen
    * @author Robert Francisti
    */
public class Inventory implements IInventory, Comparable<Inventory> {

    /**
     * is the maxWeight the inventory can hold
     */
    final private int maxWeight;

    /**
     * is the ID of the inventory
     */
    final private int id;

    /**
     * is an arrayList of IItem to hold the items in inventory
     */
    private ArrayList<Item> items = new ArrayList<>();

    /**
     * a static variable to always create an inventory with a new ID
     */
    private static int nextID;

    /**
     * constructer for the Inventory
     *
     * @param maxWeight the maxWeight of the inventory being constructed
     */
    public Inventory(int maxWeight) {
        this.maxWeight = maxWeight;
        id = nextID;
        nextID++;
    }

    /**
     * constructor for the inventory
     *
     * @param inventory takes and IInventory and creates an Inventory
     */
    public Inventory(IInventory inventory) {
        this.maxWeight = inventory.getMaxWeight();
        this.id = inventory.getInventoryID();
        for (IItem item : inventory.getItems()) {
            Item newItem;
            switch (item.getName()) {
                case BLOODBAG:
                    newItem = new BloodBag((IBloodBag) item);
                    break;
                case MORPHINE:
                case BANDAGE:
                    newItem = new PowerUpItem((IPowerUpItem) item);
                    break;
                case IDCARD:
                    newItem = new IDCard(item.getWeight(), ItemName.IDCARD);
                    break;
                default:
                    throw new AssertionError(item.getName().name());

            }
            items.add(newItem);
        }

        if (nextID <= id) {
            nextID = id + 1;
        }
    }

    /**
     * adds an item to the inventory, only if it does not exceed the maxWeight
     *
     * @param item is the item being added to inventory
     * @return true if the item has been added, false if not
     */
    public boolean addItem(IItem item) {
        if (item.getWeight() + getTotalWeight() <= maxWeight) {
            return items.add((Item) item);
        } else {
            return false;
        }
    }

    /**
     * removes an item from the inventory
     *
     * @param item is the item being removed
     * @return true if the item has been removed, false if not
     */
    public boolean removeItem(IItem item) {
        return items.remove((Item) item);
    }

    /**
     *
     * @param index is the index of the item being retrieved from items
     * ArrayList
     * @return an IItem object
     */
    @Override
    public Item getItem(int index) {
        return items.get(index);
    }

    /**
     * returns an item
     *
     * @param item equivalent item te be retrieved
     * @return an item
     */
    public Item getItem(IItem item) {
        int i = items.indexOf(item);
        if (i != -1) {
            return items.get(i);
        }
        return null;
    }

    /**
     * returns IItems
     *
     * @return the whole ArrayList items
     */
    @Override
    public ArrayList<? extends IItem> getItems() {
        return items;
    }

    /**
     * calculates weight of all items the in inventory
     *
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
     *
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

    /**
     * getter method
     *
     * @return ID of inventory
     */
    @Override
    public int getInventoryID() {
        return id;
    }

    /**
     * equals method to check if two inventories are the same
     *
     * @param obj the inventory to check
     * @return true if they are the same, false if not
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Inventory) {
            return ((Inventory) obj).getInventoryID() == id;
        }
        return false;
    }

    /**
     * new hashCode for the inventory
     *
     * @return the int hashCode
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        return hash;
    }

    /**
     * comparator for inventories
     *
     * @param o is the inventory to be compared
     * @return if id of 1st inventory is bigger than 2nd inventory returns 1 if
     * id of 1st inventory is lower than that of the 2nd inventory returns -1
     * else returns 0
     */
    @Override
    public int compareTo(Inventory o) {
        if (id > o.getInventoryID()) {
            return 1;
        }
        if (id >= o.getInventoryID()) {
        } else {
            return -1;
        }
        return 0;
    }

    /**
     *
     * @return maxWeight of inventory
     */
    @Override
    public int getMaxWeight() {
        return maxWeight;
    }

    /**
     * resets inventory ID
     */
    public static void resetID() {
        nextID = 0;
    }

}
