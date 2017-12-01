/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Data;

import business.Room;
import common.BloodType;
import common.IInventory;
import common.IPlayer;
import common.IPowerUpItem;
import common.IRoom;
import java.util.ArrayList;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class DataPlayer implements IPlayer {
    private BloodType bloodType;
    private Room currentRoom;
    private int bloodAmount;
    private double bloodRate;
    private int inventoryID;
    private IInventory inventory;
    private String name;
    private ArrayList<? extends IPowerUpItem> activeItems;
    
    public void DataPlayer(IPlayer player) {
       this.activeItems = player.getActiveItems();
    }

    @Override
    public IRoom getCurrentRoom() {
        return currentRoom;
    }

    @Override
    public BloodType getBloodType() {
        return bloodType;
    }

    @Override
    public int getBloodAmount() {
        return bloodAmount;
    }


    @Override
    public double getBloodRate() {
        return bloodRate;
    }

    @Override
    public int getInventoryID() {
        return inventoryID;
    }

    @Override
    public IInventory getInventory() {
        return inventory;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ArrayList<? extends IPowerUpItem> getActiveItems() {
       return activeItems;
    }

}
