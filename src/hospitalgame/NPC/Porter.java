package hospitalgame.NPC;

import hospitalgame.*;
import java.util.List;



/**
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Tobias Ahrenschneider Sztuk
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public class Porter extends Move {

    /**
     * endRoom holds the location of the doctor
     */
    private Room endRoom;

    /**
     * Calls the NPC constructor through the super
     *
     * @param name name of the NPC moving
     * @param description description of the NPC that moves
     */
    public Porter(String name, String description) {
        super(name, description);
    }

    /**
     * sets a room to endRoom.
     *
     * @param endRoom endRoom is the room where doctor is located.
     */
    public void setEndRoom(Room endRoom) {
        this.endRoom = endRoom;
    }

    /**
     * Overrides the abstract method in NPC
     *
     * @param player is the player object
     */
    @Override
    public void interact(Player player) {      
        System.out.print("These directions will lead you two rooms ahead ");
        List<String> path = Map.pathfinder(player.getCurrentRoom(), endRoom);
        for (int i = 0; i < 2 && i < path.size(); i++) {
            System.out.print(path.get(i) + " ");
        }
        System.out.println();
    }
}
