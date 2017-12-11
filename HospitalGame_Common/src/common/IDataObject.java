package common;

/**
 * DataObject interface
 *
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public interface IDataObject {
    
    /**
     * Gets the player.
     * @return The player.
     */
    public IPlayer getPlayer();

    /**
     * Gets all the inventories.
     * @return All the inventories.
     */
    public IInventory[] getInventories();

    /**
     * Gets the NPCs
     * @return The NPCs
     */
    public INPC[] getNPCs();

    /**
     * Gets the rooms
     * @return The rooms.
     */
    public IRoom[] getRooms();
}
