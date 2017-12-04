/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import common.BloodType;
import common.IInventory;
import common.IPlayer;
import common.IPowerUpItem;
import common.IRoom;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class DataPlayer implements IPlayer, Serializable {

    private BloodType bloodType;
    private int currentRoom;
    private int bloodAmount;
    private double bloodRate;
    private int inventoryID;
    private String name;
    private DataPowerUpItem[] activeItems;
    private boolean bloodTypeKnown;

    public DataPlayer(IPlayer player) {
        this.activeItems = new DataPowerUpItem[player.getActiveItems().size()];
        for (int i = 0; i < player.getActiveItems().size(); i++) {
            activeItems[i] = new DataPowerUpItem(player.getActiveItems().get(i));
        }
        bloodType = player.getBloodType();
        currentRoom = player.getCurrentRoomID();
        bloodAmount = player.getBloodAmount();
        bloodRate = player.getBloodRate();
        inventoryID = player.getInventoryID();
        name = player.getName();
        bloodTypeKnown = player.isBloodTypeKnown();
        
    }

    @Override
    public IRoom getCurrentRoom() {
        throw new UnsupportedOperationException("Invalid operation for data object.");
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
        throw new UnsupportedOperationException("Invalid operation for data object.");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<? extends IPowerUpItem> getActiveItems() {
        return Arrays.asList(activeItems);
    }

    @Override
    public int getCurrentRoomID() {
        return currentRoom;
    }

    @Override
    public boolean isBloodTypeKnown() {
        return bloodTypeKnown;
    }

}
