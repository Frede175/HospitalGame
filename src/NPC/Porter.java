package NPC;

import hospitalgame.Player;
import hospitalgame.Room;

/**
 *
 * @author Tobias
 */
public class Porter extends Move {
    
    /**
     * Calls the NPC constructor through the super
     * @param currentRoom the room that the NPC is in right now
     * @param name name of the NPC moving 
     * @param description decription of the NPC that moves
     */
    public Porter(Room currentRoom, String name, String description){
        super(currentRoom, name, description);
    }

    /**
     * Overrides the abstract method in NPC
     * @param player is the player object
     */
    @Override
    public void interact(Player player) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
    
}
