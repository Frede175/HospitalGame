package hospitalgame.NPC;

import hospitalgame.*;

/**
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Tobias Ahrenschneider Sztuk
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public class Computer extends NPC {

    /**
     * Calls the NPC constructor through the super
     *
     * @param name name of the NPC
     * @param description decription of the NPC
     */
    public Computer(String name, String description) {
        super(name, description);

    }

    /**
     * Overrides the abstract method in NPC
     *
     * @param player is the player object
     */
    @Override
    public void interact(Player player) {
        System.out.println("You have the blood type : " + player.getBloodType());
    }

}
