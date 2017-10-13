package hospitalgame.NPC;
import hospitalgame.*;

/**
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Tobias Ahrenschneider Sztuk
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public class Doctor extends NPC{
    
    /**
     * Calls the NPC constructor through the super
     * @param currentRoom the room that the NPC is in right now
     * @param name name of the NPC  
     * @param description decription of the NPC 
     */
    public Doctor (Room currentRoom, String name, String description){
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
