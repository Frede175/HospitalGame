/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Data;

import common.Directions;
import common.ICoordinate;
import common.IInventory;
import common.IRoom;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class DataRoom implements IRoom {

    private String name;
    private HashMap<Directions, IRoom> exits;
    private boolean inspected;
    private ICoordinate coordinate;
    private IInventory inventory;
    private boolean isLocked;

    @Override
    public IRoom getExit(Directions dir) {
        return exits.get(dir);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isLocked() {
       return isLocked;
    }

    @Override
    public boolean isInspected() {
         return inspected;
    }

    @Override
    public Set<Directions> getExitDirections() {
        return exits.keySet();
    }

    @Override
    public ICoordinate getCoordinate() {
        return coordinate;

    }

    @Override
    public IInventory getInventory() {
        return inventory;
    }

}
