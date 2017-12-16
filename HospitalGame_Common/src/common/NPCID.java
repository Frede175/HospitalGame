package common;

/**
 * NPCID interface
 *
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public enum NPCID {
    DOCTOR("Doctor"),
    COMPUTER("Computer"),
    PORTER("Porter");

    /**
     * Contains the NPC ID.
     */
    private String name;

    /**
     * Construct an NPCID.
     * @param name Which ID to use.
     */
    private NPCID(String name) {
        this.name = name;
    }

    /**
     * Gets the NPC ID.
     * @return The NPC ID.
     */
    @Override
    public String toString() {
        return name;
    }

}
