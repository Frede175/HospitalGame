
package hospitalgame.item;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Tobias Ahrenschneider Sztuk
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public enum ItemName {

    BLOODBAG("Blood bag"), BANDAGE("Bandage"), MORPHINE("Morphine");
    
    private String name;    
    
    private ItemName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        throw new NotImplementedException();
    }

}
