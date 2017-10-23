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
    ArrayList<Item> items = new ArrayList<>();

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
            return items.add(item);
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
        return items.remove(item);
    }

    /**
     * prints items from the inventory to console
     */
    public void showItems() {
        for (int i = 0; i <= items.size(); i++) {
            System.out.println(i + items.get(i).toString());
        }
    }

    /**
     * returns the totalWeight of the items currently in the inventory
     *
     * @return the total weight in the inventory
     */
    public int getTotalWeight() {
        int totalWeight = 0;
        for (Item item : items) {
            totalWeight += item.getWeight();
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

        for (Item item : items) {
            if (name == item.getName()) {
                itemList.add(item);
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
        return items.get(index);
    }

}
