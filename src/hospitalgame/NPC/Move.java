package hospitalgame.NPC;

import hospitalgame.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Tobias Ahrenschneider Sztuk
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public abstract class Move extends NPC {

    /**
     * Calls the NPC constructor through the super
     *
     * @param name name of the NPC moving
     * @param description decription of the NPC that moves
     */
    public Move(String name, String description) {
        super(name, description);
    }

    /**
     * makes the NPC move to a new room
     */
    public void move() {

        List<String> keyList = new ArrayList<>(currentRoom.getKeySet());

        int numberOfExits = keyList.size();
        int index = (int) (Math.random() * numberOfExits);
        currentRoom = currentRoom.getExit(keyList.get(index));

    }
    

    /**
     * has no function, but will call a method of the same name at a object
     *
     * @param player is a reference to the player
     */
    @Override
    public abstract void interact(Player player);
}
