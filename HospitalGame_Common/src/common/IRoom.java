package common;

import java.util.Set;

/**
 * Room interface
 *
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public interface IRoom {

    /**
     * Gets an room from the rooms exit.
     * @param dir Which direction the exit is.
     * @return The room of the direction.
     */
    IRoom getExit(Direction dir);
    
    /**
     * Gets the room ID from the rooms exit.
     * @param dir Which direction the exit is.
     * @return The room ID of the direction.
     */
    int getExitID(Direction dir);

    /**
     * Gets the name of the room.
     * @return The name of the room.
     */
    String getName();

    /**
     * Gets all the exit directions of the room.
     * @return 
     */
    Set<Direction> getExitDirections();

    /**
     * Checks if the room is locked.
     * @return True if the room is locked.
     */
    boolean isLocked();

    /**
     * Checks if the room has been inspected by the player.
     * @return True if the room is inspected.
     */
    boolean isInspected();

    /**
     * Gets the coordinate for the room
     * @return The coordinate for the room.
     */
    ICoordinate getCoordinate();

    /**
     * Gets the rooms inventory.
     * @return The rooms inventory.
     */
    IInventory getInventory();
    
    /**
     * Gets the rooms inventory ID.
     * @return The rooms inventory ID.
     */
    int getInventoryID();

    /**
     * Gets the room ID
     * @return The room ID.
     */
    int getRoomID();
}
