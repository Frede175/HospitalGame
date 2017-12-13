/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import business.Item.PowerUpItem;
import business.common.IItemFacade;
import business.common.IMoveable;
import common.BloodType;
import common.Direction;
import common.GameConstants;
import common.IInventory;
import common.IItem;
import common.IPlayer;
import common.IPowerUpItem;
import common.IRoom;
import common.ItemName;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class to execute all player functions
 *
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public class Player implements IPlayer, IMoveable {

    /**
     * access to itemFacade from player
     */
    private IItemFacade itemFacade;

    /**
     * how mcuh blood the player has
     */
    private double bloodAmount;

    /**
     * how fast the player loses blood
     */
    private double bloodRate;

    /**
     * the time of the last update
     */
    private long lastUpdate;

    /**
     * The time when the player last moved
     */
    private long lastMove;

    /**
     * an ArrayList of the items currently active
     */
    private ArrayList<PowerUpItem> activeItems = new ArrayList<>();

    /**
     * access to business facade
     */
    private BusinessFacade businessFacade;

    /**
     * the inventoryID of the player's inventory
     */
    private int inventoryID;

    /**
     * the room that the player is currently in
     */
    private int roomID;

    /**
     * the blood type of the player
     */
    private BloodType bloodType;

    /**
     * Weather or not the player know his blood type.
     */
    private boolean bloodTypeKnown;

    /**
     * Reference to map
     */
    private Map map;

    /**
     * If the game is paused
     */
    private boolean isPaused = false;

    /**
     *
     * @return the current room of the player
     */
    @Override
    public IRoom getCurrentRoom() {
        return map.getRoomByID(roomID);
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
     * @return how much blood the player has left
     */
    @Override
    public int getBloodAmount() {
        return (int) bloodAmount;
    }

    /**
     *
     * @return the rate at which the player loses blood
     */
    @Override
    public double getBloodRate() {
        update();
        return calculateBloodRate();
    }

    /**
     * getter for activeItems arrayList
     *
     * @return activeItems
     */
    @Override
    public ArrayList<? extends IPowerUpItem> getActiveItems() {
        return activeItems;
    }

    /**
     * Constructor for player
     *
     * @param bloodType is the bloodType for the player to hold
     * @param bloodRate is the rate that the player loses blood
     * @param bloodAmount is how much blood the player has
     * @param itemFacade is the facade for the player
     */
    public Player(BloodType bloodType, double bloodRate, double bloodAmount, IItemFacade itemFacade) {
        this.bloodType = bloodType;
        this.bloodRate = bloodRate;
        this.bloodAmount = bloodAmount;
        this.itemFacade = itemFacade;

        inventoryID = itemFacade.createInventory(GameConstants.INVENTORY_MAX_WEIGHT);

        lastUpdate = System.currentTimeMillis();

        activeItems = new ArrayList<>();
    }

    /**
     * Constructor for player
     *
     * @param player is the dataPlayer to be restored
     */
    public Player(IPlayer player) {
        bloodType = player.getBloodType();
        bloodRate = player.getBloodRate();
        bloodAmount = player.getBloodAmount();
        activeItems = new ArrayList<>();
        for (IPowerUpItem item : player.getActiveItems()) {
            activeItems.add((PowerUpItem) item);
        }
        inventoryID = player.getInventoryID();
        bloodTypeKnown = player.isBloodTypeKnown();
        roomID = player.getCurrentRoomID();
    }

    /**
     * injector for business facade
     *
     * @param businessFacade is the facade to be injected
     */
    public void injectBusinessFacade(BusinessFacade businessFacade) {
        this.businessFacade = businessFacade;
    }

    /**
     * injector for item facade
     *
     * @param itemFacade is the facade to be injected
     */
    public void injectItemFacade(IItemFacade itemFacade) {
        this.itemFacade = itemFacade;
    }

    /**
     * injector for map
     *
     * @param map map is the class to be injected
     */
    public void injectMap(Map map) {
        this.map = map;
    }

    /**
     * uses an item
     *
     * @param index is the index of the item to be used
     * @return if the item is used or an error happened
     */
    public boolean useItem(int index) {
        IItem item = itemFacade.getInventory(inventoryID).getItem(index);

        if (item != null) { //Checking if the given index has an item
            if (item instanceof PowerUpItem) { //refactor? item isstandsof PowerUpItem
                PowerUpItem power = (PowerUpItem) item;
                power.startBuff(System.currentTimeMillis());
                activeItems.add(power);
                itemFacade.removeItem(inventoryID, item);

                if (getNumberOfItemInActiveItems(ItemName.MORPHINE) >= 3) //    Morphine overdose
                {
                    businessFacade.setGameOver();
                }
                return true;
            }

        }
        return false;
    }

    /**
     * moves the player
     *
     * @param direction is the direction
     * @return true if the player has moved
     */
    @Override
    public boolean move(Direction direction) {
        Room nextRoom = (Room) map.getRoomByID(roomID).getExit(direction);

        if (nextRoom == null) {
            return false;
        }
        if (nextRoom.isLocked()) {
            if (itemFacade.getInventory(inventoryID).getItemsByName(ItemName.IDCARD).length > 0) {
                roomID = nextRoom.getRoomID();
                map.getRoomByID(roomID).setInspected();
                lastMove = System.currentTimeMillis();
                return true;
            }
        } else {
            roomID = nextRoom.getRoomID();
            map.getRoomByID(roomID).setInspected();
            lastMove = System.currentTimeMillis();
            return true;
        }
        return false;
    }

    /**
     * drops an item
     *
     * @param item is the item to be dropped
     * @return true if the item has been dropped
     */
    public boolean dropItem(IItem item) {
        if (itemFacade.addItem(map.getRoomByID(roomID).getInventoryID(), item)) {
            itemFacade.removeItem(inventoryID, item);
            return true;
        }
        return false;
    }

    /**
     * Takes the item with the given index from the current room the player is
     * in.
     *
     * @param index the item index in the room
     * @return if the item was taken or not.
     */
    public boolean takeItem(int index) {
        if (itemFacade.addItem(inventoryID, getCurrentRoom().getInventory().getItem(index))) {
            itemFacade.removeItem(getCurrentRoom().getInventory().getInventoryID(), getCurrentRoom().getInventory().getItem(index));
            return true;
        }
        return false;
    }

    /**
     * sets the currentRoom
     *
     * @param roomID is the new room to become the current Room
     */
    public void setCurrentRoom(int roomID) {
        this.roomID = roomID;
    }

    /**
     * updates the game, blood and active items
     */
    public void update() {
        if (isPaused) {
            return;
        }
        long current = System.currentTimeMillis();
        long diff = current - lastUpdate;
        lastUpdate = current;

        //The player loses bloodRate every sec.
        double loss = bloodRate * diff / 1000;

        //Using iterator to loop through, since we need to be able to remove a item the from the list
        for (Iterator<PowerUpItem> iterator = activeItems.iterator(); iterator.hasNext();) {
            PowerUpItem item = (PowerUpItem) iterator.next(); //Getting the next item in the list

            long timeLeftBeforeUpdate = item.getTimeLeftOfBuff();

            item.update(current);

            //using an if to see if the value is negativ, since we don't want to affect the remaing time that need to taking care of.
            long powerDiff = timeLeftBeforeUpdate - (item.getTimeLeftOfBuff() < 0 ? 0 : item.getTimeLeftOfBuff());

            loss -= item.getBuff() * powerDiff / 1000;

            if (item.getTimeLeftOfBuff() <= 0) { // Buff is no longer active!
                iterator.remove();
            }
        }
        loss = loss < 0 ? 0 : loss;
        bloodAmount -= loss;

        if (bloodAmount <= 0) {
            businessFacade.setGameOver();
        }

    }

    /**
     * returns the amount of a certain item by name
     *
     * @param item is the item to find
     * @return the number of active items with the given ItemName
     */
    private int getNumberOfItemInActiveItems(ItemName item) {
        int count = 0;
        for (PowerUpItem activeItem : activeItems) {
            if (activeItem.getName() == item) {
                count++;
            }
        }
        return count;
    }

    /**
     * calculates the new blood amount
     *
     * @return the amount of blood to lose
     */
    private double calculateBloodRate() {
        double loss = bloodRate;

        for (IPowerUpItem item : activeItems) {
            loss -= item.getBuff();
        }
        loss = loss < 0 ? 0 : loss;
        return loss;

    }

    /**
     *
     * @return an IInventory
     */
    @Override
    public IInventory getInventory() {
        return itemFacade.getInventory(inventoryID);
    }

    /**
     *
     * @return the ID of the inventory that the player "owns"
     */
    @Override
    public int getInventoryID() {
        return inventoryID;
    }

    /**
     *
     * @return the ID of the room the player is in.
     */
    @Override
    public int getCurrentRoomID() {
        return roomID;
    }

    /**
     * Set bloodTypeknown to be true
     */
    public void setBloodTypeKnown() {
        bloodTypeKnown = true;
    }

    /**
     * Pause the player update
     */
    public void pause() {
        setLastUpdate(-1);
        isPaused = true;
    }

    /**
     * Resumes the player update
     */
    public void resume() {
        isPaused = false;
        setLastUpdate(System.currentTimeMillis());
        update();
    }

    /**
     * Set the last update on player and on all active items.
     *
     * @param time
     */
    private void setLastUpdate(long time) {
        lastUpdate = time;
        for (PowerUpItem item : activeItems) {
            item.setLastUpdate(lastUpdate);
        }
    }

    /**
     *
     * @return true if the player knows the blood type
     */
    @Override
    public boolean isBloodTypeKnown() {
        return bloodTypeKnown;
    }

    /**
     *
     * @return the time for when the player last moved
     */
    @Override
    public long getLastMove() {
        return lastMove;
    }
}
