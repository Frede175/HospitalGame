package hospitalgame.NPC;

import hospitalgame.*;
import java.util.Scanner;

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
    Scanner scanner = new Scanner(System.in);

    /**
     * Description of the NPC
     */
    private String description;

    /**
     * Room object to tell which the NPC is in
     */
    protected Room currentRoom;

    /**
     * Constructor of the NPC objects
     *
     * @param name is the name of the NPC
     * @param description gives a decription of the NPC
     */
    public NPC(String name, String description) {
        this.description = description;
        this.name = name;
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
    public void setRoom(Room currentRoom){
        this.currentRoom = currentRoom;
    }

    /**
     * has no function, but will call a method of the same name at a object
     *
     * @param player is a reference to the player
     */
    public abstract void interact(Player player);
}
