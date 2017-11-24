package business.Item;

import common.IItem;
import common.ItemName;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class Item implements IItem {

    
    private int weight;
    private ItemName name;
    
    public Item(int weight, ItemName name) {
        this.name = name;
        this.weight = weight;
    }
    
    @Override
    public int getWeight() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItemName getName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
