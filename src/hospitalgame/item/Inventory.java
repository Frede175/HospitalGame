package hospitalgame.item;

import java.util.ArrayList;

/**
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Tobias Ahrenschneider Sztuk
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public class Inventory {

    /**
     * maxWeight contains the max weight that the inventory can hold
     */
    private int maxWeight;
    /**
     * is an arraylist to hold the items in the inventory
     */
    ArrayList<Item> inventory = new ArrayList<>();

    /**
     * constructs and initializes the inventory
     *
     * @param maxWeight the max weight of the inventory
     */
    public Inventory(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    /**
     * adds an item to the inventory
     *
     * @param item is the item object to be added to the inventory.
     * @return returns true if the item has been added
     */
    public boolean addItem(Item item) {
        if (item.getWeight() + getTotalWeight() <= maxWeight) {
            return inventory.add(item);
        } else {
            return false;
        }

    }

    /**
     * removes and item from the inventory
     *
     * @param item the item object to be added
     * @return returns true if the item has been removed
     */
    public boolean removeItem(Item item) {
        return inventory.remove(item);
    }

    /**
     * prints items from the inventory to console
     */
    public void showItems() {
        for (int i = 0; i <= inventory.size(); i++) {
            System.out.println(i + inventory.get(i).toString());
        }
    }

    /**
     * returns the totalWeight of the items currently in the inventory
     *
     * @return the total weight in the inventory
     */
    public int getTotalWeight() {
        int totalWeight = 0;
        for (int i = 0; i < inventory.size(); i++) {
            totalWeight += inventory.get(i).getWeight();
        }
        return totalWeight;
    }

    /**
     * returns an array of items from the inventory with the name specified
     *
     * @param name is the name of the item(s) to be returned
     * @return an array of items with the specified name
     */
    public Item[] getItemsByName(ItemName name) {

        ArrayList<Item> itemList = new ArrayList<>();

        for (int i = 0; i <= inventory.size(); i++) {
            if (name.toString().equals(inventory.get(i).toString())) {
                itemList.add(inventory.get(i));
            }
        }
        Item[] item = new Item[itemList.size()];
        itemList.toArray(item);
        return item;
    }

    /**
     * showIventory prints out the items with index numbers getItem returns the
     * item by number
     *
     * @param index is the index number of the item to be returned
     * @return returns an item object
     */
    public Item getItem(int index) {
        return inventory.get(index);
    }

}
