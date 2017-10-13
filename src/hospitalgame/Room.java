package hospitalgame;

import java.util.Set;
import java.util.HashMap;
import hospitalgame.item.*; //hospitalgame.item package imported
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
    
    public boolean addItem(Item item){
        return true;
    }
    
    public boolean removeItem(Item item){
        return true;
    }
    
    public Item getItem(int index){
        return null;
    }
    
    public void showItem(){
        
    }
}

