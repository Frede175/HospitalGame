
package hospitalgame.item;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Tobias Ahrenschneider Sztuk
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public class Inventory {

    private int maxWeight;

    public Inventory(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public boolean addItem(Item item) {
        throw new NotImplementedException();
    }

    public boolean removeItem(Item item) {
        throw new NotImplementedException();
    }

    public void showItems() {

    }

    public int getTotalWeight() {
        throw new NotImplementedException();
    }

    public Item[] getItemsByName(ItemName name) {
        throw new NotImplementedException();
    }

    public Item getItem(int index) {
        throw new NotImplementedException();
    }

}
