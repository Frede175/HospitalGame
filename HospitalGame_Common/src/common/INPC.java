package common;

/**
 * NPC interface.
 *
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public interface INPC {

    /**
     * Gets the current room for the NPC.
     * @return The current room for the NPC.
     */
    IRoom getCurrentRoom();

    /**
     * Checks if the NPC can move.
     * @return True if the NPC can move.
     */
    boolean canMove();

    /**
     * Gets the name of the NPC.
     * @return The name of the NPC.
     */
    String getName();

    /**
     * Gets the NPC ID.
     * @return The NPC ID.
     */
    NPCID getNPCID();

    /**
     * Gets the current room ID for the NPC.
     * @return The current room ID for the NPC.
     */
    public int getCurrentRoomID();

}
