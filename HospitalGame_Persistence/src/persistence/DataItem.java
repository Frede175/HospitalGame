/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import common.IItem;
import common.ItemName;
import java.io.Serializable;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class DataItem implements IItem, Serializable {

    private int weight;
    private ItemName name;
    
    public DataItem(IItem item) {
        weight = item.getWeight();
        name = item.getName();        
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public ItemName getName() {
        return name;
    }


}
