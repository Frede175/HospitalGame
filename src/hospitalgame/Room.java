package hospitalgame;

import java.util.Set;
import java.util.HashMap;
import hospitalgame.item.*; //hospitalgame.item package imported
import java.util.ArrayList;
import java.util.Iterator;



/**
 * Room contains all information about a room.
 * 
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Tobias Ahrenschneider Sztuk
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 * @author Michael Kolling and David J. Barnes
 */
public class Room 
{
    /**
     * Contains the description of the room
     */
    private String description;
    /**
     * Contains all the exit doors in the room.
     */
    private HashMap<String, Room> exits;
    
    /**
     * ArrayList for which items that is in the room
     */
    private ArrayList<Item> itemArray;

    /**
     * Construct and initialize the room.
     * @param description The description of the room.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
    }

    /**
     * Sets an exit door in the room. The neighbor room is the room the door leads to.
     * @param direction The direction the door is in the room.
     * @param neighbor The neighbor room.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * Gets the rooms description.
     * @return The description.
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Gets the long description of the room.
     * @return The long description.
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    /**
     * Gets the exit string.
     * @return The exit string.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Gets the room based on the player command.
     * @param direction The direction the player wants to go.
     * @return The room based on the player command.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    /**
     * Tells how many exits a room has
     * @return a integer that tells the size of our Hashmap
     */
    public int getNumberOfExits(){
        return exits.size();
    }
    
    /**
     * Make it possible to add a specific item to a room
     * @param item is an Item object that tells which item should be added
     * @return true if it is possible to drop an item from the inventory
     */
    public boolean addItem(Item item){
        
        return itemArray.add(item);
    }
    
    /**
     * Makes it possible to remove a specific item in the current room
     * @param item is which item that should be removed from the room 
     * @return true if it is possible for the player to pick up the item, adding it to the inventory and removes it from the room
     */
    public boolean removeItem(Item item){
        return itemArray.remove(item);
    }
    
    /**
     * Finds a item in the ArrayList, if nothing is found it will return null
     * @param index is which index of the ArrayList that the item should be found
     * @return an specific Item object 
     */    
    public Item getItem(int index){
        return itemArray.get(index);
    }
    
    /**
     * Display which items are currently in the room
     * 
     */
    public void showItem(){
        System.out.println("Current items in the room:");
        for (int i = 0; i < itemArray.size(); i++) {
            System.out.println("Index: " + i + "\t" + itemArray.get(i).getName() + "\t" + itemArray.get(i).getWeight() + "g, ");
        }
    }
}

