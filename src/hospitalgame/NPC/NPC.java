package hospitalgame.NPC;

import hospitalgame.*;

/**
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Tobias Ahrenschneider Sztuk
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public abstract class NPC {

    /**
     * Name of the NPC
     */
    private String name;

    /**
     * Description of the NPC
     */
    private String description;

    /**
     * Room object to tell which the NPC is in
     */
    protected Room currentRoom;
    /**
     * canMove checks if objects can move.
     */
    private boolean canMove;
    /**
     * Constructor of the NPC objects
     *
     * @param name is the name of the NPC
     * @param description gives a decription of the NPC
     * @param canMove checks if an object can move. 
     */
    public NPC(String name, String description, boolean canMove) {
        this.description = description;
        this.name = name;
        this.canMove = canMove;     
    }
    
    /**
     *
     * @return which room the NPC is in
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     *
     * @return the name of the NPC
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return the description of which NPC the player is interacting with
     */
    public String getDescription() {
        return description;
    }

    /**
     * setRoom sets currentRoom 
     * @param currentRoom the room needs to be set
     */
    public void setRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * getCanMove creates a returntype for canMove. 
     * @return returns canMove.
     */
    public boolean getCanMove(){
        return canMove;
    }

    /**
     * has no function, but will call a method of the same name at a object
     *
     * @param player is a reference to the player
     */
    public abstract void interact(Player player);
}
