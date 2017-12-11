package common;

/**
 * ItemNames
 *
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public enum ItemName {
    BLOODBAG("Blood bag"),
    BANDAGE("Bandage"),
    MORPHINE("Morphine"),
    IDCARD("ID card");

    /**
     * Contains the name.
     */
    private String name;

    private ItemName(String name) {
        this.name = name;
    }

    /**
     * Gets the name.
     * @return 
     */
    @Override
    public String toString() {
        return name;
    }

}
