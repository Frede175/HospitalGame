package hospitalgame.NPC;

import hospitalgame.*;
import hospitalgame.item.BloodBag;
import hospitalgame.item.Item;
import hospitalgame.item.ItemName;

/**
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Tobias Ahrenschneider Sztuk
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public class Doctor extends NPC {

    /**
     * Calls the NPC constructor through the super
     *
     * @param currentRoom the room that the NPC is in right now
     * @param name name of the NPC
     * @param description decription of the NPC
     */
    public Doctor(Room currentRoom, String name, String description) {
        super(currentRoom, name, description);
    }

    /**
     * Overrides the abstract method in NPC
     *
     * @param player is the player object
     */
    @Override
    public void interact(Player player) {
        BloodType bloodType = player.getBloodType();

        BloodBag[] bloodBags = (BloodBag[]) player.getItemsByName(ItemName.BLOODBAG);
        for (BloodBag bloodBag : bloodBags) {
            if (!bloodType.canTransfuse(bloodBag.getBloodType())) {
                System.out.println("You lost the game.");
                return;

            }

        }
        int points = (int) player.getBloodAmount();
        for (BloodBag bloodBag : bloodBags) {
            points += bloodBag.getBonusPoints();

        }
        System.out.println("You've earned " + points + " points! ");
    }
    
}
