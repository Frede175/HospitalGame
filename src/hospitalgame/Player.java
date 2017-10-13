package hospitalgame;

import hospitalgame.item.Inventory;
import hospitalgame.item.Item;
import hospitalgame.item.ItemName;
import java.util.ArrayList;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Tobias Ahrenschneider Sztuk
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public class Player {
    
    /**
     * The players blood type
     */
    private BloodType bloodType;
    
    /**
     * The room the player is currently in
     */
    private Room currentRoom;
    
    /**
     * Players inventory that holds the players items
     */
    private Inventory inventory;
    
    /**
     * The player name
     */
    private String name;
    
    /**
     * The amount of blood the player has left
     */
    private double bloodAmount;
    
    /**
     * The default amount of blood the player loses
     */
    private double bloodRate;
    
    /**
     * When the last update to bloodAmount happened
     */
    private long lastUpdate;
    
    /**
     * The items that are active. These items apply effects on the player.
     */
    private ArrayList<Item> activeItems;
    
    
    /**
     * Constructor for player
     * @param bloodType the player have.
     * @param bloodRate the default amount of blood the player losses.
     * @param bloodAmount the amount of blood the player.
     * @param name player name.
     * @param startRoom the room the player starts in.
     */
    public Player(BloodType bloodType, double bloodRate, double bloodAmount, String name, Room startRoom) {
        this.bloodType = bloodType;
        this.bloodRate = bloodRate;
        this.bloodAmount = bloodAmount;
        this.name = name;
        this.currentRoom = startRoom;
        
        inventory = new Inventory(2000);
        
        lastUpdate = 0;
        
        activeItems = new ArrayList<>();
    }
    
    /**
     * Uses the item that is specified in the command.
     * @param command the use item command
     */
    public void useItem(Command command) {
        throw new NotImplementedException();
    }
    
    /**
     * Added the item to the inventory.
     * @param item The item the be added
     * @return true if the item is successfully added to the inventory
     */
    public boolean addItem(Item item) {
        return inventory.addItem(item);
    }
    
    /**
     * Removes the current item 
     * @param item the item to be removed
     * @return true if the item is removed successfully from the inventory.
     */
    public boolean removeItem(Item item) {
        return inventory.removeItem(item);
    }
    
    /**
     * Get the current room that the player is in.
     * @return the current room.
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }
    
    /**
     * The blood amount the player has.
     * @return the blood amount.
     */
    public double getBloodAmount() {
        return bloodAmount;
    }
    
    /**
     * Print the contents of the inventory to the console.
     */
    public void showInventory() {
        inventory.showItems();
    }
    
    /**
     * Gets the item the specified name. 
     * @param name The name of items 
     * @return an array of items that has the specified name.
     */
    public Item[] getItemsByName(ItemName name) {
        return inventory.getItemsByName(name);
    }
    
    /**
     * Goes the to next room with the given exit. 
     * @param command the go command
     * @return true if the player was able to go the requested room
     */
    public boolean goRoom(Command command) {
        throw new NotImplementedException();
    }
    
    /**
     * Process the take item command.
     * @param command the take item command
     */
    public void takeItem(Command command) {
        throw new NotImplementedException();
    }
    
    /**
     * Calculates the current blood loss and updates the players blood amount. 
     */
    private void update() {
        throw new NotImplementedException();
    }
}
