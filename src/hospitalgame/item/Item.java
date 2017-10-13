
package hospitalgame.item;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Tobias Ahrenschneider Sztuk
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public class Item {

    private int weight;
    private ItemName name;
    
    
    public Item(ItemName name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public ItemName getName() {
        throw new NotImplementedException();
    }

    public int getWeight() {
        throw new NotImplementedException();
    }

}
