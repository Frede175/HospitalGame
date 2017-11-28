/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import business.common.IItemFacade;
import common.Directions;
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
    private String name;
    private HashMap<Directions, IRoom> exits;
    private boolean inspected = false;
    private int inventoryID;
    private boolean locked;
    private Coordinate c;

    /**
     * constructor for room. inspected Is set to false as standard.
     *
     * @param name is the name assigned to the room.
     */
    public Room(String name) {
        this.name = name;
    }

    public void injectItemFacade(IItemFacade itemFacade) {
        this.itemFacade = itemFacade;
        this.inventoryID = itemFacade.createInventory(2000);
    }

    public void setExit(Directions direction, IRoom roomNeighbour) {
        exits.put(direction, roomNeighbour);
    }

    public boolean addItem(IItem item) {
        return itemFacade.addItem(inventoryID, item);
    }

    public boolean removeItem(IItem item) {
        return itemFacade.removeItem(inventoryID, item);
    }

    public IItem getItem(int index) {
        return itemFacade.getInventory(inventoryID).getItem(index);
    }

    @Override
    public IRoom getExit(Directions direction) {
        return exits.get(direction);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Set<Directions> getExitDirections() {
        return exits.keySet();
    }

    @Override
    public boolean isLocked() {
        return locked;
    }

    @Override
    public boolean isInspected() {
        return inspected;
    }
    public Coordinate getCoordinate(){
        return c;
    }
    public void setCoordinate(Coordinate c){
        this.c = c;
    }

  

    


}
