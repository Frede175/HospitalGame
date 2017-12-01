/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import business.common.IItemFacade;
import common.Directions;
import common.GameConstants;
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
    private HashMap<Directions, Integer> exits;
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
        this.roomID = nextID;
        nextID++;
    }
    public Room(IRoom room){
        roomID = room.getRoomID();
        if (roomID >= nextID) nextID = roomID + 1;
        
        inventoryID = room.getInventoryID();
        c = new Coordinate(room.getCoordinate().getX(), room.getCoordinate().getY());
        name = room.getName();
        exits = new HashMap<>();
        for (Directions dir : room.getExitDirections()) {
            exits.put(dir, room.getExitID(dir));
        }
        locked = room.isLocked();
        
    }

    /**
     * injector for the item facede
     *
     * @param itemFacade is the item facade to be injected
     */
    public void injectItemFacade(IItemFacade itemFacade) {
        this.itemFacade = itemFacade;
        this.inventoryID = itemFacade.createInventory(GameConstants.INVENTORY_MAX_WEIGHT);
    }
    
    public void injectMap(Map map) {
        this.map = map;
    }

    /**
     * sets exit for a room
     *
     * @param direction the direction of the exit
     * @param roomNeighbour is the room that i leads to
     */
    public void setExit(Directions direction, IRoom roomNeighbour) {
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
    public IRoom getExit(Directions direction) {
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
    public Set<Directions> getExitDirections() {
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

    @Override
    public IInventory getInventory() {
        return itemFacade.getInventory(inventoryID);
    }

    public void setInspected() {
        this.inspected = true;
    }

    @Override
    public int getRoomID() {
        return roomID;
    }

    @Override
    public int getExitID(Directions dir) {
        return exits.get(dir);
    }

}
