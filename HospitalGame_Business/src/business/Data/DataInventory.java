/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Data;

import business.Item.Item;
import common.IInventory;
import common.IItem;
import common.ItemName;
import java.util.ArrayList;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class DataInventory implements IInventory {

    private ArrayList<IItem> items;
    private Integer maxWeight;
    private IItem getItem;
    private int inventoryID;
    private ItemName name;

    @Override
    public IItem getItem(int index) {
        return items.get(index);
    }

    @Override
    public ArrayList<IItem> getItems() {
        return items;
    }

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

    @Override
    public int getInventoryID() {
        return inventoryID;
    }

    @Override
    public int getMaxWeight() {
        return maxWeight;
    }

}
