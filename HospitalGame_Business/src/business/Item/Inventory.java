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
public class Inventory {

    private int maxWeight;
    private int id;
    private ArrayList<Item> items = new ArrayList<>();
    private int nextID;

    public Inventory(int maxWeight) {

    }

    public Inventory(IInventory inventory) {

    }

    public boolean addItem(IItem item) {
        return false;
    }

    public boolean removeItem(IItem item) {
        return false;
    }

    public IItem getItem(int index) {
        return null;
    }

    public ArrayList<IItem> getItems() {
        return null;
    }

    public IItem[] getItemByName(ItemName name) {
        return null;
    }

    public int getTotalWeight() {
        return 0;
    }
}
