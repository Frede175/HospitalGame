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
 * Class to store player
 * 
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public class DataPlayer implements IPlayer, Serializable {

    /**
     * The blood type of the player
     */
    private BloodType bloodType;
    
    /**
     * The ID of the current room the player is in
     */
    private int currentRoom;
    
    /**
     * The amount of blood the player has left
     */
    private int bloodAmount;
    
    /**
     * The amount of blood the player losses per second 
     */
    private double bloodRate;
    
    /**
     * The ID of the inventory
     */
    private int inventoryID;
    
    /**
     * The items that are active such as bandage or morphine
     */
    private DataPowerUpItem[] activeItems;
    
    /**
     * weather the player knows their own blood type
     */
    private boolean bloodTypeKnown;

    /**
     * Constructor for data player from the interface IPlayer
     * @param player the given player
     */
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
        bloodTypeKnown = player.isBloodTypeKnown();
    }

    /**
     * NO SUPPORTED BY DATA OBJECT
     * @return NOT SUPPORTED
     */
    @Override
    public IRoom getCurrentRoom() {
        throw new UnsupportedOperationException("Invalid operation for data object.");
    }

    /**
     * 
     * @return the blood type of the player
     */
    @Override
    public BloodType getBloodType() {
        return bloodType;
    }

    /**
     * 
     * @return the amount of blood the player has left
     */
    @Override
    public int getBloodAmount() {
        return bloodAmount;
    }

    /**
     * 
     * @return the amount of blood the player losses per second
     */
    @Override
    public double getBloodRate() {
        return bloodRate;
    }

    /**
     * 
     * @return the ID of the players inventory
     */
    @Override
    public int getInventoryID() {
        return inventoryID;
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
     * @return the active items
     */
    @Override
    public List<? extends IPowerUpItem> getActiveItems() {
        return Arrays.asList(activeItems);
    }

    /**
     * 
     * @return the ID of the room the player is in
     */
    @Override
    public int getCurrentRoomID() {
        return currentRoom;
    }

    /**
     * 
     * @return weather the player knows his/hers blood type
     */
    @Override
    public boolean isBloodTypeKnown() {
        return bloodTypeKnown;
    }

}
