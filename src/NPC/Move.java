package NPC;
import hospitalgame.*;

/**
 *
 * @author Tobias
 */
public abstract class Move extends NPC{
    
    /**
     * Calls the NPC constructor through the super
     * @param currentRoom the room that the NPC is in right now
     * @param name name of the NPC moving 
     * @param description decription of the NPC that moves
     */
    public Move(Room currentRoom, String name, String description){
        super(currentRoom, name, description);
    }
    
    /**
     * makes the NPC move to a new room
     */
    public void move(){
        
    }
    
     /**
     * has no function, but will call a method of the same name at a object
     * @param player is a reference to the player
     */
    @Override
    public abstract void interact(Player player);
}
