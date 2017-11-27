/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.util.ArrayList;

/**
 *
 * @author fsr19
 */
public interface IInventory {
    IItem getItem(int index);
    ArrayList<? extends IItem> getItems();
    IItem[] getItemsByName(ItemName name);
    int getInventoryID();
    int getMaxWeight();
}