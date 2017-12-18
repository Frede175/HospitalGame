package common;

import java.util.List;

/**
 * Player interface
 *
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public interface IPlayer {

    /**
     * Gets the current room for the player.
     * @return The current room for the player.
     */
    IRoom getCurrentRoom();

    /**
     * Gets the bloodtype of the player.
     * @return The bloodtype of the player.
     */
    BloodType getBloodType();

    /**
     * Gets the bloodamount remaining of the player.
     * @return The bloodamount remaining of the player.
     */
    int getBloodAmount();

    /**
     * Gets the bloodrate of the player.
     * @return The bloodrate of the player.
     */
    double getBloodRate();

    /**
     * Gets all the active items of the player.
     * @return All the active items of the player.
     */
    List<? extends IPowerUpItem> getActiveItems();

    /**
     * Gets the players inventory ID.
     * @return The players inventory ID.
     */
    int getInventoryID();

    /**
     * Gets the players inventory.
     * @return The players inventory.
     */
    IInventory getInventory();

    /**
     * Gets the current room ID of the player.
     * @return The current room ID of the player.
     */
    public int getCurrentRoomID();
    
    /**
     * Checks if the player know its own bloodtype.
     * @return True if the player know its own bloodtype.
     */
    boolean isBloodTypeKnown();

}
