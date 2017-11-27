/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.NPC;

import business.Item.BloodBag;
import business.Item.Item;
import business.Player;
import common.BloodType;
import common.IBusiness;
import common.INPC;
import common.IPlayer;
import common.IRoom;
import common.ItemName;
import common.NPCID;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class Doctor extends NPC {

    private IBusiness business; 
    /**
     * Constructor for Doctor
     *
     * @param name name of the NPC
     * @param canMove boolean true if the NPC can move
     * @param currentRoom the room the NPC being created to be in
     * @param npcId the NPCID of the NPC
     */
    public Doctor(String name, boolean canMove, IRoom currentRoom, NPCID npcId) {
        super(name, canMove, currentRoom, npcId);
    }

    public Doctor(INPC npc) {
        super(npc.getName(), npc.canMove(), npc.getCurrentRoom(), npc.getNPCID());
    }

    public void interact(Player player) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String interact(IPlayer player) {

        BloodType bloodType = player.getBloodType();
        Item[] bloodBags = (Item[]) player.getInventory().getItemsByName(ItemName.BLOODBAG);
        int points = (int) player.getBloodAmount();
        if (bloodBags.length != 0) {
            for (Item Item : bloodBags) {
                BloodBag bloodBag = (BloodBag) Item;
                if (!bloodType.canTransfuse(bloodBag.getBloodType())) {
                    return ("You lost the game.");
                    //Game.getGameInstance().setGameOver();
                } else {
                    points += bloodBag.getBonusPoints();
                }
            }
            return ("You've earned " + points + " points! ");
            // Game.getGameInstance().setGameOver();
        } else {
            return ("You need to get a bloodbag");
        }
    }
}

}
