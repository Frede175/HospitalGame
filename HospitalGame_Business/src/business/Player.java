/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import business.Item.PowerUpItem;
import business.common.IItemFacade;
import common.BloodType;
import common.Directions;
import common.IInventory;
import common.IItem;
import common.IPlayer;
import common.IPowerUpItem;
import common.IRoom;
import common.ItemName;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class Player implements IPlayer {

    /**
     * access to itemFacade from player
     */
    private IItemFacade itemFacade;
    
    /**
     * name of the player
     */
    private String name;
    
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
    private Room currentRoom;
    
    /**
     * the blood type of the player
     */
    private BloodType bloodType;

    /**
     * 
     * @return the current room of the player 
     */
    @Override
    public IRoom getCurrentRoom() {
        return currentRoom;
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
        return bloodRate;
    }

    /**
     * getter for activeItems arrayList
     * @return activeItems
     */
    @Override
    public ArrayList<? extends IPowerUpItem> getActiveItems() {
        return activeItems;
    }

    /**
     * Constructor for player
     * @param bloodType is the bloodType for the player to hold
     * @param bloodRate is the rate that the player loses blood
     * @param bloodAmount is how much blood the player has
     * @param name is the name of the player
     */
    public Player(BloodType bloodType, double bloodRate, double bloodAmount, String name) {
        this.bloodType = bloodType;
        this.bloodRate = bloodRate;
        this.bloodAmount = bloodAmount;
        this.name = name;

        inventoryID = itemFacade.createInventory(2000);

        lastUpdate = System.currentTimeMillis();

        activeItems = new ArrayList<>();
    }
    
    /**
     * Constructor for player 
     * @param player is the dataPlayer to be restored
     */
    public Player(IPlayer player) {
        this.bloodType = player.getBloodType();
        this.bloodRate = player.getBloodRate();
        this.bloodAmount = player.getBloodAmount();
        this.name = player.getName();
    }

    /**
     * injector for business facade
     * @param businessFacade is the facade to be injected
     */
    public void injectBusinessFacade(BusinessFacade businessFacade) {
        this.businessFacade = businessFacade;
    }

    /**
     * injector for item facade
     * @param itemFacade is the facade to be injected
     */
    public void injectItemFacade(IItemFacade itemFacade) {
        this.itemFacade = itemFacade;
    }
    
    /**
     * uses an item
     * @param index is the index of the item to be used
     */
    public void useItem(int index) {
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
            }
        }
    }

    /**
     * moves the player
     * @param direction is the direction 
     * @return true if the player has moved
     */
    public boolean move(Directions direction) {
        Room nextRoom = (Room) currentRoom.getExit(direction);

        if (nextRoom == null) {
            return false; }
        if (nextRoom.isLocked()) {
            if (itemFacade.getInventory(inventoryID).getItemsByName(ItemName.IDCARD).length > 0) {
                currentRoom = nextRoom;
                return true;
            }
        } else {
            currentRoom = nextRoom;
            return true;
        }
        return false;
    }

    /**
     * takes an item from the current room
     * @param item is the item to take
     * @return true if the item has been added to the player inventory
     */
    public boolean takeItem(IItem item) {
        if (itemFacade.addItem(inventoryID, item)) {
            itemFacade.removeItem(currentRoom.getInventoryID(), item);
            return true;
        }
        return false;
    }

    /**
     * drops an item
     * @param item is the item to be dropped
     * @return true if the item has been dropped
     */
    public boolean dropItem(IItem item) {
        if (itemFacade.removeItem(inventoryID, item)) {
            itemFacade.addItem(currentRoom.getInventoryID(), item);
            return true;
        }
        return false;
    }

    /**
     * sets the currentRoom 
     * @param currentRoom is the new room to become the current Room
     */
    public void setCurrentRoom(IRoom currentRoom) {
        this.currentRoom = (Room) currentRoom;
    }

    /**
     * updates the game
     * blood and active items
     */
    private void update() {
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
        bloodAmount -= loss;

        if (bloodAmount <= 0) {
            businessFacade.setGameOver();
        }

    }

    /**
     * returns the amount of a certain item by name
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
     * @return the amount of blood to lose
     */
    private double calculateLoss() {
        double loss = bloodRate;

        for (IPowerUpItem item : activeItems) {
            loss -= item.getBuff();
        }
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
     * @return the name of the player
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * 
     * @return the ID of the inventory that the player "owns"
     */
    @Override
    public int getInventoryID() {
        return inventoryID;
    }

}
