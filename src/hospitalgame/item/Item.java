package hospitalgame.item;

/**
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Tobias Ahrenschneider Sztuk
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public abstract class Item {

    /**
     * weight is the weight of the item name is the name of the item
     */
    private int weight;
    private ItemName name;

    /**
     * constructs and initializes the Item
     *
     * @param name is the name of the item to be constructed
     * @param weight is the weight of the item to be constructed
     */
    public Item(ItemName name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    /**
     * is a method to get the name of the item
     *
     * @return returns enum name of the item
     */
    public ItemName getName() {
        return name;
    }

    /**
     * is a method to get the weight of the item
     *
     * @return returns int the weight of the item
     */
    public int getWeight() {
        return weight;
    }

    @Override
    public abstract String toString();
}
