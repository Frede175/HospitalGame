package NPC;
import hospitalgame.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author Tobias
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
    private Room currentRoom;
    
    /**
     * Constructor of the NPC objects
     * @param currentRoom is which room the NPC is in
     * @param name is the name of the NPC
     * @param description gives a decription of the NPC
     */
    public NPC(Room currentRoom, String name, String description){

    }
    
    /**
     * 
     * @return which room the NPC is in
     */
    public Room getCurrentRoom(){
        throw new NotImplementedException();
    }
    
    /**
     * 
     * @return the name of the NPC
     */
    public String getName(){
        throw new NotImplementedException();
    }
    
    /**
     * 
     * @return the description of which NPC the player is interacting with
     */
    public String getDescription(){
        throw new NotImplementedException();
    }
    
    /**
     * has no function, but will call a method of the same name at a object
     * @param player is a reference to the player
     */
    public abstract void interact(Player player);
}
