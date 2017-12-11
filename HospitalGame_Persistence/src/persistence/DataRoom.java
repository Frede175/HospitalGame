package persistence;

import common.Direction;
import common.ICoordinate;
import common.IInventory;
import common.IRoom;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

/**
 * Class to store room 
 * 
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public class DataRoom implements IRoom, Serializable {

    /**
     * The name of room
     */
    private String name;
    
    /**
     * Which connections it has
     */
    private HashMap<Direction, Integer> exits;
    
    /**
     * Weather the player has been to it or not
     */
    private boolean inspected;
    
    /**
     * The coordinate for the room (start room is 0,0)
     */
    private DataCoordinate coordinate;
    
    /**
     * The ID of the rooms inventory
     */
    private int inventoryID;
    
    /**
     * Weather is locked or not
     */
    private boolean isLocked;
    
    /**
     * The ID of the room
     */
    private int roomID;

    /**
     * Constructor for data room from the interface IRoom
     * @param room the given room
     */
    public DataRoom(IRoom room) {
        name = room.getName();
        exits = new HashMap<>();
        for (Direction dir : room.getExitDirections()) {
            exits.put(dir, room.getExit(dir).getRoomID());
        }
        inspected = room.isInspected();
        inventoryID = room.getInventory().getInventoryID();
        coordinate = new DataCoordinate(room.getCoordinate());
        isLocked = room.isLocked();
        roomID = room.getRoomID();
    }

    /**
     * NO SUPPORTED BY DATA OBJECT 
     * @param dir
     * @return NOT SUPPORTED
     */
    @Override
    public IRoom getExit(Direction dir) {
        throw new UnsupportedOperationException("Invalid operation for data object.");
    }

    /**
     * 
     * @return the name of the room
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * 
     * @return if the room is locked
     */
    @Override
    public boolean isLocked() {
        return isLocked;
    }

    /**
     * 
     * @return if the player has been to the room
     */
    @Override
    public boolean isInspected() {
        return inspected;
    }

    /**
     * 
     * @return what exits the room has
     */
    @Override
    public Set<Direction> getExitDirections() {
        return exits.keySet();
    }

    /**
     * 
     * @return the coordinate for the room
     */
    @Override
    public ICoordinate getCoordinate() {
        return coordinate;

    }

    /**
     * NO SUPPORTED BY DATA OBJECT
     * @return NOT SUPPORTED
     */
    @Override
    public IInventory getInventory() {
        throw new UnsupportedOperationException("Invalid operation for data object.");
    }

    /**
     * 
     * @return the ID of the room
     */
    @Override
    public int getRoomID() {
        return roomID;
    }

    /**
     * 
     * @return the ID of the rooms inventory
     */
    @Override
    public int getInventoryID() {
        return inventoryID;
    }

    /**
     * Get a room from a exit
     * @param dir the exit
     * @return the room at that exit
     */
    @Override
    public int getExitID(Direction dir) {
        return exits.get(dir);
    }

}
