/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fsr19
 */
public interface IInventory {

    IItem getItem(int index);

    List<? extends IItem> getItems();

    IItem[] getItemsByName(ItemName name);

    int getInventoryID();

    int getMaxWeight();
}
