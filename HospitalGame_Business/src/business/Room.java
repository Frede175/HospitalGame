/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import business.common.IItemFacade;
import common.Direction;
import common.ICoordinate;
import common.IInventory;
import common.IItem;
import common.IRoom;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class Room implements IRoom {

    private IItemFacade itemFacade;
    private Map map;
    private String name;
    private HashMap<Direction, Integer> exits;
    private boolean inspected = false;
    private int inventoryID;
    private boolean locked;
    private Coordinate c;
    private int roomID;
    public static int nextID = 0;

    /**
     * constructor for room. inspected Is set to false as standard.
     *
     * @param name is the name assigned to the room.
     */
    public Room(String name) {
        this.name = name;
        exits = new HashMap<>();
        inventoryID = -1;
        this.roomID = nextID;
        nextID++;
    }
    
    /**
     * constructor for room. This is for when a room is loaded from persistence
     * 
     * @param room The room that need to be converted to a "real" room.
     */
    public Room(IRoom room){
        roomID = room.getRoomID();
        if (roomID >= nextID) nextID = roomID + 1;
        
        inventoryID = room.getInventoryID();
        c = new Coordinate(room.getCoordinate().getX(), room.getCoordinate().getY());
        name = room.getName();
        exits = new HashMap<>();
        for (Direction dir : room.getExitDirections()) {
            exits.put(dir, room.getExitID(dir));
        }
        locked = room.isLocked();
        inspected = room.isInspected();
        
    }

    /**
     * injector for the item facade
     *
     * @param itemFacade is the item facade to be injected
     */
    public void injectItemFacade(IItemFacade itemFacade) {
        this.itemFacade = itemFacade;
        if (inventoryID == -1) {
            inventoryID = itemFacade.createInventory(Integer.MAX_VALUE);
        }
    }
    
    public void injectMap(Map map) {
        this.map = map;
    }
    
    /**
     * Set if the room is locked 
     * @param locked weather the room is locked or not.
     */
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    /**
     * sets exit for a room
     *
     * @param direction the direction of the exit
     * @param roomNeighbour is the room that i leads to
     */
    public void setExit(Direction direction, IRoom roomNeighbour) {
        exits.put(direction, roomNeighbour.getRoomID());
    }

    /**
     * adds an item to a room's inventory
     *
     * @param item is the item to be added
     * @return true if the item has been added
     */
    public boolean addItem(IItem item) {
        return itemFacade.addItem(inventoryID, item);
    }

    /**
     *
     * @return the room's inventoryID
     */
    @Override
    public int getInventoryID() {
        return inventoryID;
    }

    /**
     * removes an item from a room's invenory
     *
     * @param item the item to be removed
     * @return true if the item has been removed
     */
    public boolean removeItem(IItem item) {
        return itemFacade.removeItem(inventoryID, item);
    }

    /**
     * returns an item from an inventory based on index
     *
     * @param index the index number to be retrieved
     * @return an IItem
     */
    public IItem getItem(int index) {
        return itemFacade.getInventory(inventoryID).getItem(index);
    }

    /**
     * find a room given a direction
     *
     * @param direction is the direction to find a room
     * @return an IRoom
     */
    @Override
    public IRoom getExit(Direction direction) {
        if (exits.get(direction) == null) return null;
        return map.getRoomByID(exits.get(direction));
    }

    /**
     * getter for name
     *
     * @return name of the room
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * get exits of a room
     *
     * @return Set of Directions
     */
    @Override
    public Set<Direction> getExitDirections() {
        return exits.keySet();
    }

    /**
     * getter for isLocked
     *
     * @return true or false if the room is locked
     */
    @Override
    public boolean isLocked() {
        return locked;
    }

    /**
     * getter for isInspected
     *
     * @return true if the room has been inspected
     */
    @Override
    public boolean isInspected() {
        return inspected;
    }

    /**
     * c is the coordinate
     *
     * @return
     * @returns the coordinate
     */
    @Override
    public ICoordinate getCoordinate() {
        return c;
    }

    /**
     * setCoordinate sets coordinate
     *
     * @param c is a coordinate
     */
    public void setCoordinate(Coordinate c) {
        this.c = c;
    }
    /**
     * 
     * @returns the inventory
     */

    @Override
    public IInventory getInventory() {
        return itemFacade.getInventory(inventoryID);
    }
/**
 * sets inspected to true
 */
    public void setInspected() {
        this.inspected = true;
    }
/**
 * 
 * @return the rooms ID 
 */
    @Override
    public int getRoomID() {
        return roomID;
    }
/**
 * 
 * @param dir is the direction
 * @return the exits at following directions
 */
    @Override
    public int getExitID(Direction dir) {
        return exits.get(dir);
    }
    /**
     * resets ID
     */
    public static void reset() {
        nextID = 0;
    }

}
