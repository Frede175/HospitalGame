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

    /**
     * constructs and initializes the ItemName
     *
     * @param name is the name of the ItemName to be constructed
     */
    private ItemName(String name) {
        this.name = name;
    }

    /**
     * returns the string name of an item
     *
     * @return returns a string name
     */
    @Override
    public String toString() {
        return name;
    }

}
