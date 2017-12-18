package persistence;

import common.IDataObject;
import common.IInventory;
import common.INPC;
import common.IPlayer;
import common.IRoom;
import java.io.Serializable;

/**
 * Class to hold:
 *  Inventories
 *  Player
 *  NPCs
 *  Rooms
 * 
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public class DataObject implements IDataObject, Serializable {
    
    /**
     * Array of rooms that needs to be stored
     */
    private DataRoom[] rooms;
    
    /**
     * The player that needs to be stored
     */
    private DataPlayer player;
    
    /**
     * The NPCs to be stored
     */
    private DataNPC[] npcs;
    
    /**
     * The inventories to be stored
     */
    private DataInventory[] inventories;

    /**
     * Constructor for data object, converts to data objects (DataItem, DataNPC, DataInventory and DataPlayer)
     * 
     * @param rooms the rooms to be stored
     * @param player the player to be stored
     * @param npcs the NPCs to be stored
     * @param inventories the inventories the be stored
     */
    public DataObject(IRoom[] rooms, IPlayer player, INPC[] npcs, IInventory[] inventories) {
        this.rooms = new DataRoom[rooms.length];
        for (int i = 0; i < rooms.length; i++) {
            this.rooms[i] = new DataRoom(rooms[i]);
        }
        this.player = new DataPlayer(player);
        this.npcs = new DataNPC[npcs.length];
        for (int i = 0; i < npcs.length; i++) {
            this.npcs[i] = new DataNPC(npcs[i]);
        }
        this.inventories = new DataInventory[inventories.length];
        for (int i = 0; i < inventories.length; i++) {
            this.inventories[i] = new DataInventory(inventories[i]);
        }
    }

    /**
     * 
     * @return the player
     */
    @Override
    public IPlayer getPlayer() {
        return player;
    }

    /**
     * 
     * @return the inventories
     */
    @Override
    public IInventory[] getInventories() {
        return inventories;
    }

    /**
     * 
     * @return the NPCs
     */
    @Override
    public INPC[] getNPCs() {
        return npcs;
    }

    /**
     * 
     * @return the rooms
     */
    @Override
    public IRoom[] getRooms() {
        return rooms;
    }

}
