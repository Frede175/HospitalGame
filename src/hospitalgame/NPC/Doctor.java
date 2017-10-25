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
     * @param name name of the NPC
     * @param description decription of the NPC
     */
    public Doctor(String name, String description) {
        super(name, description);
    }

    /**
     * Overrides the abstract method in NPC
     *
     * @param player is the player object
     */
    @Override
    public void interact(Player player) {
        BloodType bloodType = player.getBloodType();

        Item[] bloodBags = player.getItemsByName(ItemName.BLOODBAG);
        int points = (int) player.getBloodAmount();
        for (Item Item : bloodBags) {
        BloodBag bloodBag = (BloodBag) Item;     
            if (!bloodType.canTransfuse(bloodBag.getBloodType())) {
                System.out.println("You lost the game.");
                return;
            } else {
                points += bloodBag.getBonusPoints();
            }

        }
        System.out.println("You've earned " + points + " points! ");
    }

}
